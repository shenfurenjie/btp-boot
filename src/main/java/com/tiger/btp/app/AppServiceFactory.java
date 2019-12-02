package com.tiger.btp.app;

import com.tiger.btp.framework.common.context.AppContextUtil;
import com.tiger.btp.framework.common.exception.BaseException;
import com.tiger.btp.model.app.App;
import com.tiger.btp.model.app.Apps;
import com.tiger.btp.model.data_model.DataModels;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;
import javax.xml.bind.JAXBException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Data
@Component
public class AppServiceFactory {

    @Autowired
    DataModelFactory dataModelFactory;

    @Autowired
    DataSource dataSource;

    /**
     * map appName:appService
     */
    Map<String, AppService> appServiceMap = new ConcurrentHashMap<>();
    @Value("${server.port}")
    String port;
    @Value("${server.servlet.context-path}")
    String basePath;
    private Object lock = new Object();


    /**
     * 创建app服务
     *
     * @param appXmlPath
     * @param dataModelXmlPath
     */
    public AppService createAppService(String appXmlPath, String[] dataModelXmlPath) {
        App app = getApp(appXmlPath);
        DataModels dataModels = getDataModels(dataModelXmlPath);
        if (appServiceMap.containsKey(app.getName())) {
            throw new BaseException("app [" + app.getName() + "] has exists , please check app name !");
        }
        AppService appService = new AppService(app, AppContextUtil.getApplicationContext(), dataModelFactory, dataSource);
        dataModels.getDataModel().forEach(dataModel -> {
            dataModelFactory.createDataModelExt(dataModel, app);
        });
        appServiceMap.put(app.getName(), appService);
        return getAppService(app.getName());
    }

    /**
     * 获取xml中的dataModel
     *
     * @param xmlLocation
     * @return
     */
    public DataModels getDataModels(String[] xmlLocation) {
        DataModels dataModels = new DataModels();
        for (String xml : xmlLocation) {
            InputStream inputStream = AppServiceFactory.class.getClassLoader().getResourceAsStream(xml);
            ModelResource<DataModels> modelResource = new ModelResource<>(DataModels.class, inputStream);
            DataModels models = modelResource.readModel();
            dataModels.getDataModel().addAll(models.getDataModel());
        }
        return dataModels;
    }


    /**
     * 获取xml中的app
     *
     * @param xmlLocation
     * @return
     * @throws JAXBException
     */
    public App getApp(String xmlLocation) {
        InputStream io = AppServiceFactory.class.getClassLoader().getResourceAsStream(xmlLocation);
        ModelResource<Apps> modelResource = new ModelResource<Apps>(Apps.class, io);
        Apps apps = modelResource.readModel();
        return apps.getApp().get(0);
    }

    /**
     * 获取app服务
     *
     * @param appName
     * @return
     */
    public AppService getAppService(String appName) {
        return appServiceMap.get(appName);
    }

    protected void assertAppExists(String appName) {
        if (!appServiceMap.containsKey(appName)) {
            throw new BaseException("app [" + appName + "] is not found!");
        }
    }


}
