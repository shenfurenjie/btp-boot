package com.tiger.btp.app;

import lombok.Data;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.Assert;

@Data
public abstract class AppServiceSupport implements InitializingBean {

    String appXmlPath;

    String[] dataModelXmlPath;

    AppService appService;

    @Autowired
    AppServiceFactory appServiceFactory;

    /**
     * 注册appService
     */
    public void init() {
        Assert.notNull(this.appXmlPath, "app xml can not be null.");
        Assert.notNull(this.dataModelXmlPath, "data model xml can not be null.");
        AppService appService = this.appServiceFactory.createAppService(this.appXmlPath, this.dataModelXmlPath);
        this.appService = appService;
    }

    public void afterPropertiesSet() {
        this.init();
    }
}
