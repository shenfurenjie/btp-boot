package com.tiger.btp.app.service;

import com.tiger.btp.framework.maven.PomXml;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;


@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class GenModelServiceTest {

    @Autowired
    GenModelService genModelService;

    @Autowired
    GenBaseAppService genBaseAppService;

    //@Test
    public void genPojoProjectAndPublishToMavenRepo() throws Exception {

        PomXml pomXml = new PomXml();
        pomXml.setName("school");
        pomXml.setArtifactId("school_model");
        pomXml.setGroupId("com.tiger.btp.school");
        pomXml.setVersion("1.0-SNAPSHOT");
        pomXml.setRepositoryURL("http://localhost:8081/repository/maven-public/");
        pomXml.setSnapshotRepositoryURL("http://localhost:8081/repository/maven-snapshots/");
        String mavenHome = "/Users/renjie/workspace/apache-maven-3.5.2";
        genModelService.genPojoProjectAndPublishToMavenRepo(pomXml, mavenHome);

    }

    @Test
    public void genBaseAppService() throws Exception {
        PomXml pomXml = new PomXml();
        pomXml.setName("school");
        pomXml.setArtifactId("school_model");
        pomXml.setGroupId("com.tiger.btp.school");
        pomXml.setVersion("1.0.3-SNAPSHOT");
        pomXml.setRepositoryURL("http://localhost:8081/repository/maven-public/");
        pomXml.setSnapshotRepositoryURL("http://localhost:8081/repository/maven-snapshots/");
        String mavenHome = "/Users/renjie/workspace/apache-maven-3.5.2";
        String supperClass = "com.baomidou.mybatisplus.core.mapper.BaseMapper";
        genBaseAppService.genBaseAppProject(pomXml, supperClass, mavenHome);
    }

}