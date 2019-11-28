package com.tiger.btp.app;

import com.tiger.btp.framework.common.utils.MavenUtil;
import com.tiger.btp.model.app.App;
import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.context.ApplicationContext;

import javax.sql.DataSource;

/**
 * app服务
 */
@Slf4j
public class AppService {
    @Setter
    @Getter
    App app;

    ApplicationContext applicationContext;

    @Setter
    @Getter
    DataModelFactory dataModelFactory;

    @Setter
    @Getter
    String modelPackageName;

    @Setter
    String host;

    @Setter
    String basePath;

    @Getter
    @Setter
    DataSource dataSource;


    public AppService(App app, ApplicationContext applicationContext) {
        this.app = app;
        this.applicationContext = applicationContext;
        this.modelPackageName = MavenUtil.getPackageName(app.getGroupId(), app.getArtifactId());
    }

    public AppService(App app, ApplicationContext applicationContext, DataModelFactory dataModelFactory) {
        this.app = app;
        this.applicationContext = applicationContext;
        this.modelPackageName = MavenUtil.getPackageName(app.getGroupId(), app.getArtifactId());
        this.dataModelFactory = dataModelFactory;
    }

    public AppService(App app, ApplicationContext applicationContext, DataModelFactory dataModelFactory, DataSource dataSource) {
        this.app = app;
        this.applicationContext = applicationContext;
        this.modelPackageName = MavenUtil.getPackageName(app.getGroupId(), app.getArtifactId());
        this.dataModelFactory = dataModelFactory;
        this.dataSource = dataSource;
    }

    public AppService(App app, ApplicationContext applicationContext, String host, String basePath) {
        this.app = app;
        this.applicationContext = applicationContext;
        this.host = host;
        this.basePath = basePath;
        this.modelPackageName = MavenUtil.getPackageName(app.getGroupId(), app.getArtifactId());
    }

}
