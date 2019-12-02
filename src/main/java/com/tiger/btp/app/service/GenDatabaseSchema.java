package com.tiger.btp.app.service;

import com.alibaba.druid.pool.DruidDataSource;
import com.tiger.btp.app.AttributeExt;
import com.tiger.btp.app.DataModelExt;
import com.tiger.btp.app.DataModelFactory;
import com.tiger.btp.framework.common.exception.BaseException;
import com.tiger.btp.framework.common.utils.FtlUtils;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.io.IOUtils;
import org.apache.commons.lang3.StringUtils;
import org.hibernate.boot.Metadata;
import org.hibernate.boot.MetadataSources;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.engine.jdbc.connections.spi.ConnectionProvider;
import org.hibernate.tool.hbm2ddl.SchemaUpdate;
import org.hibernate.tool.schema.TargetType;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.InputStream;
import java.sql.SQLException;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 加载dataModel时，生成数据库schema
 *
 * @Author: TigerRen
 * @Date: 2019/11/25 5:01 PM
 */
@Slf4j
@Service
public class GenDatabaseSchema implements InitializingBean {


    @Autowired
    DruidDataSource dataSource;


    @Autowired
    DataModelFactory dataModelFactory;

    Map<String, String> settings = new ConcurrentHashMap<>();


    @Override
    public void afterPropertiesSet() {
        settings.put("hibernate.connection.driver_class", dataSource.getDriverClassName());
        String dialect = "org.hibernate.dialect.MySQL5InnoDBDialect";
        if (dataSource.getDriverClassName().indexOf("oracle") > -1) {

        } else if (dataSource.getDriverClassName().indexOf("sqlserver") > -1) {
            dialect = "org.hibernate.dialect.SQLServer2012Dialect";
        } else if (dataSource.getDriverClassName().indexOf("postgresql") > -1) {
            dialect = "org.hibernate.spatial.dialect.postgis.PostgisPG92Dialect";
        }
        settings.put("hibernate.dialect", dialect);
        settings.put("hibernate.connection.url", dataSource.getUrl());
        settings.put("hibernate.connection.username", dataSource.getUsername());
        settings.put("hibernate.connection.password", dataSource.getPassword());
        settings.put("hibernate.connection.pool_size ", "2");
        settings.put("hibernate.hbm2ddl.auto", "update");
        if (dialect.toLowerCase().indexOf("postgre") > -1 || dialect.toLowerCase().indexOf("postgis") > -1) {
            settings.put("hibernate.jdbc.lob.non_contextual_creation", "true");
        }
        settings.put("show_sql", "true");
    }

    /**
     * ddl操作
     *
     * @param dataModelExtList
     * @param drop
     */
    public void ddl(List<DataModelExt> dataModelExtList, boolean drop) {
        if (CollectionUtils.isEmpty(dataModelExtList)) {
            throw new BaseException("no available dataModel to ddl");
        }
        MetadataSources metadata = new MetadataSources((new StandardServiceRegistryBuilder()).applySettings(settings).build());
        for (DataModelExt dataModelExt : dataModelExtList) {
            if (dataModelExt.isView() || dataModelExt.isVirtual()) {
                continue;
            }
            InputStream xmlInputStream = new ByteArrayInputStream(genHbmFile(dataModelExt).getBytes());
            metadata.addInputStream(xmlInputStream);
        }
        genTable(metadata);

    }


    /**
     * 生成表
     *
     * @param metadata
     */
    private void genTable(MetadataSources metadata) {
        SchemaUpdate schemaUpdate = new SchemaUpdate();
        schemaUpdate.setHaltOnError(true);
        schemaUpdate.setFormat(true);
        schemaUpdate.setDelimiter(";");
        Metadata meta = metadata.buildMetadata();
        schemaUpdate.execute(EnumSet.of(TargetType.STDOUT, TargetType.DATABASE), meta);
        ConnectionProvider connectionProvider = metadata.getServiceRegistry().getService(ConnectionProvider.class);
        try {
            connectionProvider.getConnection().close();
        } catch (SQLException e) {
            e.printStackTrace();
            throw new RuntimeException(e);
        }
    }


    private String genHbmFile(DataModelExt modelExt) {
        Map<String, Object> vars = new HashMap();
        vars.put("hbmClass", getHbmClass(modelExt));
        vars.put("hbmIdColumn", getHbmIdColumn(modelExt));
        vars.put("hbmColumns", getHbmColumns(modelExt));
        try {
            String tpl = "myTemplates/gen/hbm-model.xml.ftl";
            String hbm = FtlUtils.render2(IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream(tpl), "utf-8"), vars);
            log.debug("hbm is {}", hbm);
            return hbm;
        } catch (Exception e) {
            throw new BaseException(e);
        }
    }


    private Map<String, String> getHbmClass(DataModelExt modelExt) {
        Map hbmClassMap = new HashMap();
        hbmClassMap.put("className", modelExt.getId());
        hbmClassMap.put("tableName", modelExt.getTableName());
        return hbmClassMap;
    }

    private Map<String, String> getHbmIdColumn(DataModelExt modelExt) {
        Map hbmIdMap = new HashMap();
        String idColumn = modelExt.getPkAttribute();
        Boolean isPkAuto = modelExt.isPkAuto();
        AttributeExt attributeExt = modelExt.getAttributeExt(idColumn);
        hbmIdMap.put("idName", attributeExt.getName());
        if (StringUtils.isEmpty(attributeExt.getColumnName())) {
            hbmIdMap.put("idColumnName", attributeExt.getName());
        } else {
            hbmIdMap.put("idColumnName", attributeExt.getColumnName());
        }
        hbmIdMap.put("isPkAuto", isPkAuto);
        hbmIdMap.put("type", attributeExt.getHbmType());
        if (isPkAuto) {
            hbmIdMap.put("genClass", "identity");
        } else {
            hbmIdMap.put("genClass", "assigned");
        }

        return hbmIdMap;
    }

    private List<Map<String, String>> getHbmColumns(DataModelExt modelExt) {
        List<Map<String, String>> hbmColumnList = new ArrayList<>();
        for (AttributeExt attributeExt : modelExt.getColumns()) {
            if (StringUtils.equals(attributeExt.getName(), modelExt.getPkAttribute())) {
                continue;
            }
            Map columnMap = new HashMap();
            columnMap.put("name", attributeExt.getName());
            if (StringUtils.isEmpty(attributeExt.getColumnName())) {
                columnMap.put("columnName", attributeExt.getName());
            } else {
                columnMap.put("columnName", attributeExt.getColumnName());
            }
            columnMap.put("type", attributeExt.getHbmType());
            hbmColumnList.add(columnMap);
        }
        return hbmColumnList;

    }


}
