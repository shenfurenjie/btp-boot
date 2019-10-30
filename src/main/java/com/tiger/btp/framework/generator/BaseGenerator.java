package com.tiger.btp.framework.generator;

import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.generator.AutoGenerator;
import com.baomidou.mybatisplus.generator.InjectionConfig;
import com.baomidou.mybatisplus.generator.config.*;
import com.baomidou.mybatisplus.generator.config.po.TableInfo;
import com.baomidou.mybatisplus.generator.config.rules.DateType;
import com.baomidou.mybatisplus.generator.config.rules.NamingStrategy;
import com.baomidou.mybatisplus.generator.engine.FreemarkerTemplateEngine;
import com.tiger.btp.framework.common.constants.BaseConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Collections;
import java.util.HashMap;
import java.util.Map;

/**
 * 代码生成器父类
 */
@Component
public class BaseGenerator {

    @Autowired
    DBConfig dbConfig;

    /**
     * 包设置
     *
     * @param packageName
     * @param moduleName
     * @return
     */
    public static PackageConfig getPackageConfig(String packageName, String moduleName) {

        // 包配置
        PackageConfig packageConfig = new PackageConfig();
        packageConfig.setModuleName(moduleName);
        packageConfig.setParent(packageName);
        packageConfig.setController("controller");
        packageConfig.setService("service");
        packageConfig.setServiceImpl("service.impl");
        packageConfig.setMapper("mapper");
        packageConfig.setEntity("model");
        //packageConfig.setXml("mapper.xml");
        return packageConfig;
    }

    public static void main(String[] args) {

    }

    /**
     * 执行生成
     *
     * @param tableName
     * @param packageName
     * @param moduleName
     */
    public void executeGenerate(String tableName, String packageName, String moduleName) {
        AutoGenerator generator = getAutoGenerator(tableName, packageName, moduleName);
        generator.execute();
    }

    /**
     * 获取AutoGenerator
     *
     * @param tableName
     * @return
     */
    public AutoGenerator getAutoGenerator(String tableName, String packageName, String moduleName) {
        return new AutoGenerator()
                // 全局配置
                .setGlobalConfig(getGlobalConfig())
                // 数据源配置
                .setDataSource(getDataSourceConfig())
                // 策略配置
                .setStrategy(getStrategyConfig(tableName))
                // 包配置
                .setPackageInfo(getPackageConfig(packageName, moduleName))
                // 注入自定义配置，可以在 VM 中使用 cfg.abc 设置的值
                .setCfg(getInjectionConfig())
                .setTemplate(getTemplateConfig())
                .setTemplateEngine(new FreemarkerTemplateEngine());
    }

    /**
     * 获取GlobalConfig
     *
     * @return
     */
    public GlobalConfig getGlobalConfig() {
        return new GlobalConfig()
                .setOutputDir(BaseConstant.javaPath)//输出目录
                .setFileOverride(false)// 是否覆盖文件
                .setActiveRecord(false)// 开启 activeRecord 模式
                .setEnableCache(false)// XML 二级缓存
                .setBaseResultMap(true)// XML ResultMap
                .setBaseColumnList(true)// XML columList
                .setKotlin(false) //是否生成 kotlin 代码
                .setOpen(false)
                .setAuthor("renjie") //作者
                //自定义文件命名，注意 %s 会自动填充表实体属性！
                .setEntityName("%s")
                .setMapperName("%sMapper")
                .setXmlName("%sMapper")
                .setServiceName("%sService")
                .setServiceImplName("%sServiceImpl")
                .setControllerName("%sRestController")
                .setIdType(IdType.ID_WORKER)
                .setDateType(DateType.ONLY_DATE);
    }

    /**
     * 数据源配置
     *
     * @return
     */
    public DataSourceConfig getDataSourceConfig() {
        DataSourceConfig dataSourceConfig = new DataSourceConfig();
        dataSourceConfig.setDbType(DbType.MYSQL);
        dataSourceConfig.setDriverName(dbConfig.getDriverClassName());
        dataSourceConfig.setUrl(dbConfig.getUrl());
        dataSourceConfig.setUsername(dbConfig.getUsername());
        dataSourceConfig.setPassword(dbConfig.getPassword());
        return dataSourceConfig;
    }

    /**
     * 获取StrategyConfig
     *
     * @param tableName
     * @return
     */
    protected StrategyConfig getStrategyConfig(String tableName) {
        //List<TableFill> tableFillList = getTableFills();
        return new StrategyConfig()
                .setCapitalMode(false)// 全局大写命名
                .setTablePrefix("t_")// 去除前缀
                .setNaming(NamingStrategy.underline_to_camel)// 表名生成策略
                //.setInclude(new String[] { "user" }) // 需要生成的表
                //自定义实体父类
                //.setSuperEntityClass("com.example.demo.framework.model.BaseModel")
                // 自定义实体，公共字段
                //.setSuperEntityColumns(new String[]{"id", "remark", "create_by", "create_time", "update_by", "update_time"})
                // 自定义 mapper 父类
                .setSuperMapperClass("com.rj.btp.framework.mapper.BaseMapper")
                // 自定义 controller 父类
                .setSuperControllerClass("com.rj.btp.framework.controller.BaseController")
                // 自定义 service 实现类父类
                .setSuperServiceImplClass("com.rj.btp.framework.service.impl.BaseServiceImpl")
                // 自定义 service 接口父类
                .setSuperServiceClass("com.rj.btp.framework.service.BaseService")
                .setLogicDeleteFieldName("enable")
                // 【实体】是否生成字段常量（默认 false）
                .setEntityColumnConstant(false)
                // 【实体】是否为构建者模型（默认 false）
                .setEntityBuilderModel(false)
                // 【实体】是否为lombok模型（默认 false）<a href="https://projectlombok.org/">document</a>
                .setEntityLombokModel(true)
                // Boolean类型字段是否移除is前缀处理
                .setEntityBooleanColumnRemoveIsPrefix(true)
                .setRestControllerStyle(true)
                .setInclude(tableName);
    }

    /**
     * 获取InjectionConfig
     *
     * @return
     */
    protected InjectionConfig getInjectionConfig() {
        return new InjectionConfig() {
            @Override
            public void initMap() {
                Map<String, Object> map = new HashMap<>();
                this.setMap(map);
            }
        }.setFileOutConfigList(Collections.singletonList(new FileOutConfig(
                "/myTemplates/mapper.xml.ftl") {
            // 自定义输出文件目录
            @Override
            public String outputFile(TableInfo tableInfo) {
                return BaseConstant.mapperPath + tableInfo.getEntityName() + "Mapper.xml";
            }
        }));
    }

    /**
     * 获取TemplateConfig
     *
     * @return
     */
    protected TemplateConfig getTemplateConfig() {

        TemplateConfig tc = new TemplateConfig();
        tc.setEntity("myTemplates/entity.java");
        tc.setMapper("myTemplates/mapper.java");
        tc.setXml("myTemplates/mapper.xml");
        tc.setService("myTemplates/service.java");
        tc.setServiceImpl("myTemplates/serviceImpl.java");
        tc.setController("myTemplates/controller.java");
        tc.setXml(null);
        return tc;
    }
}
