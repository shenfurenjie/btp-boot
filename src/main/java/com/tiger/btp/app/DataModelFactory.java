package com.tiger.btp.app;

import com.alibaba.fastjson.JSON;
import com.tiger.btp.framework.common.utils.MavenUtil;
import com.tiger.btp.model.app.App;
import com.tiger.btp.model.data_model.Attribute;
import com.tiger.btp.model.data_model.AttributeType;
import com.tiger.btp.model.data_model.DataModel;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * dataModel管理工厂
 */
@Slf4j
@Component
public class DataModelFactory implements DisposableBean {

    protected Object lock = new Object();
    protected boolean changed = false;
    String ADD_TIME = "addTime";
    String ADD_USER = "addUser";
    String UPDATE_TIME = "updateTime";
    String UPDATE_USER = "updateUser";
    String IS_DELETE = "isDelete";
    String defaultDateFormatPattern;
    /**
     * dataModel的容器
     */
    @Setter
    @Getter
    Map<String, DataModelExt> dataModelExtMap = new ConcurrentHashMap<>();

    @Override
    public void destroy() {

    }


    /**
     * 判断dataModel是否在容器中存在
     *
     * @param modelId
     * @return
     */
    public boolean exists(String modelId) {
        return dataModelExtMap.containsKey(modelId);
    }

    /**
     * 根据ID获取dataModel
     *
     * @param modelId
     * @return
     */
    public DataModelExt findById(String modelId) {
        return dataModelExtMap.get(modelId);
    }


    /**
     * 创建dataModel
     *
     * @param dataModel
     */
    public void createDataModelExt(DataModel dataModel, App app) {
        DataModelExt ext;
        if (dataModel instanceof DataModelExt) {
            ext = (DataModelExt) dataModel;
        } else {
            //appendDefaultAttribute(dataModel);
            ext = JSON.parseObject(JSON.toJSONString(dataModel), DataModelExt.class);
            //ext.setDataModel(dataModel);
            ext.setDataModelFactory(this);
            ext.setBasePackageName(MavenUtil.getPackageName(app.getGroupId(), app.getArtifactId()));
        }
        dataModelExtMap.put(dataModel.getId(), ext);

        //生成graphql的schema
        //refreshGraphQLSchema();
    }


    protected void appendDefaultAttribute(DataModel dataModel) {
        if (dataModel == null) {
            return;
        }

        Attribute addTime = new Attribute();
        addTime.setName(ADD_TIME);
        addTime.setType(AttributeType.TIMESTAMP);
        addTime.setColumnName("add_time");
        if (StringUtils.isNotBlank(defaultDateFormatPattern)) {
            addTime.setDateFormat(defaultDateFormatPattern);
        }
        Attribute addUser = new Attribute();
        addUser.setName(ADD_USER);
        addUser.setType(AttributeType.STRING);
        addUser.setColumnName("add_user");

        Attribute updateTime = new Attribute();
        updateTime.setName(UPDATE_TIME);
        updateTime.setType(AttributeType.TIMESTAMP);
        updateTime.setColumnName("update_time");
//        updateTime.setDateFormat("yyyy-MM-dd hh:mm:ss");
        if (StringUtils.isNotBlank(defaultDateFormatPattern)) {
            updateTime.setDateFormat(defaultDateFormatPattern);
        }
        Attribute updateUser = new Attribute();
        updateUser.setName(UPDATE_USER);
        updateUser.setType(AttributeType.STRING);
        updateUser.setColumnName("update_user");

        Attribute delete = new Attribute();
        delete.setName(IS_DELETE);
        delete.setType(AttributeType.BOOLEAN);
        delete.setColumnName("is_delete");

        appendDefaultAttribute(dataModel, addTime);
        appendDefaultAttribute(dataModel, addUser);
        appendDefaultAttribute(dataModel, updateTime);
        appendDefaultAttribute(dataModel, updateUser);
        appendDefaultAttribute(dataModel, delete);
    }

    protected void appendDefaultAttribute(DataModel dataModel, Attribute toAdd) {
        if (dataModel == null || toAdd == null) {
            return;
        }
        boolean exists = false;
        for (Attribute attribute : dataModel.getAttribute()) {
            if (toAdd.getName().equalsIgnoreCase(attribute.getName())) {
                exists = true;
                break;
            }
        }
        if (!exists) {
            dataModel.getAttribute().add(toAdd);
        }
    }


}
