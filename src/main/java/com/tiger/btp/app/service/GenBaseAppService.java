package com.tiger.btp.app.service;

import com.tiger.btp.app.AppService;
import com.tiger.btp.app.AppServiceFactory;
import com.tiger.btp.app.DataModelExt;
import com.tiger.btp.framework.common.constants.BaseConstant;
import com.tiger.btp.framework.common.exception.BaseException;
import com.tiger.btp.framework.common.utils.FtlUtils;
import com.tiger.btp.framework.common.utils.MavenUtil;
import com.tiger.btp.framework.maven.PomXml;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.FileUtils;
import org.apache.commons.io.FilenameUtils;
import org.apache.commons.io.IOUtils;
import org.apache.maven.shared.invoker.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.util.*;

/**
 * 生成app基础功能代码
 */
@Slf4j
@Service
public class GenBaseAppService {


    @Autowired
    AppServiceFactory appServiceFactory;

    /**
     * 生成app基础功能工程
     *
     * @param pomXml
     */
    public void genBaseAppProject(PomXml pomXml, String supperMapperClass, String mavenHomePath) throws IOException, MavenInvocationException {
        String tempFileName = BaseConstant.tmpdir.getPath() + "/" + UUID.randomUUID().toString();
        File tmpFile = new File(tempFileName);
        genProjectFile(pomXml, supperMapperClass, tmpFile);
        publishToMaven(tmpFile, mavenHomePath);
    }

    public void publishToMaven(File tmpFile, String mavenHomePath) throws MavenInvocationException {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File(tmpFile.getPath() + "/pom.xml"));
        request.setGoals(Collections.singletonList("clean deploy  -Dmaven.test.skip=true"));
        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File(mavenHomePath));
        InvocationResult result = invoker.execute(request);
        if (result.getExitCode() != 0) {
            throw new IllegalStateException("Build failed.");
        }
    }

    private void genProjectFile(PomXml pomXml, String supperMapperClass, File tmpFile) throws IOException {
        File javaFile = new File(tmpFile.getPath() + "/src/main/java");
        FileUtils.forceMkdir(javaFile);
        FileUtils.forceMkdir(new File(tmpFile.getPath() + "/src/test/java"));
        String pom = genProjectPomXml(pomXml);
        FileUtils.writeStringToFile(new File(tmpFile.getPath() + "/pom.xml"), pom, "utf-8");

        AppService appService = appServiceFactory.getAppService(pomXml.getName());
        Map<String, DataModelExt> dataModelExtMap = appService.getDataModelFactory().getDataModelExtMap();
        Iterator iterator = dataModelExtMap.keySet().iterator();
        while (iterator.hasNext()) {
            String modelId = (String) iterator.next();
            DataModelExt ext = dataModelExtMap.get(modelId);
            ext.setDataModelFactory(appService.getDataModelFactory());
            String packageName = MavenUtil.getPackageName(pomXml.getGroupId(), pomXml.getArtifactId());
            String fileName = packageName.replace(".", "/");
            File packageDir = new File(FilenameUtils.concat(javaFile.getPath(), fileName));
            if (!packageDir.exists()) {
                FileUtils.forceMkdir(packageDir);
            }


            String baseModel = genBaseModelBaseJavaFile(ext, packageName);
            log.debug("baseModel is {}", baseModel);
            FileUtils.writeStringToFile(new File(packageDir.getPath() + "/model/" + ext.getBaseClassName() + ".java"), baseModel, "utf-8");
            String model = genBaseModelJavaFile(ext, packageName);
            log.debug("model is {}", model);
            FileUtils.writeStringToFile(new File(packageDir.getPath() + "/model/" + ext.getClassName() + ".java"), model, "utf-8");
            String baseMapper = genBaseMapperJavaFile(ext, packageName, supperMapperClass);
            log.debug("baseMapper is {}", baseMapper);
            FileUtils.writeStringToFile(new File(packageDir.getPath() + "/mapper/" + ext.getMapperName() + ".java"), baseMapper, "utf-8");
        }
    }

    /**
     * 生成pom文件
     *
     * @param pomXml
     * @return
     */
    public String genProjectPomXml(PomXml pomXml) {
        Map<String, Object> vars = new HashMap();
        vars.put("prop", pomXml);
        try {
            String file = "myTemplates/gen/pom.xml";
            String pom = FtlUtils.render2(IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream(file), "utf-8"), vars);
            return pom;
        } catch (Exception var6) {
            throw new BaseException(var6);
        }
    }

    /**
     * 生成Mapper的java文件
     *
     * @param modelExt
     * @param basePackageName
     * @param superMapperClass
     * @return
     */
    private String genBaseMapperJavaFile(DataModelExt modelExt, String basePackageName, String superMapperClass) {
        Map<String, Object> vars = new HashMap();
        vars.put("model", modelExt);
        vars.put("basePackageName", basePackageName);
        vars.put("superMapperClass", superMapperClass);
        try {
            String tpl = "myTemplates/gen/data-model-mapper.java.ftl";
            String pom = FtlUtils.render2(IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream(tpl), "utf-8"), vars);
            return pom;
        } catch (Exception e) {
            throw new BaseException(e);
        }
    }

    /**
     * 生成model的java文件
     *
     * @param modelExt
     * @param basePackageName
     * @return
     */
    private String genBaseModelBaseJavaFile(DataModelExt modelExt, String basePackageName) {
        Map<String, Object> vars = new HashMap();
        vars.put("model", modelExt);
        vars.put("basePackageName", basePackageName);
        try {
            String tpl = "myTemplates/gen/data-model-base.java.ftl";
            String pom = FtlUtils.render2(IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream(tpl), "utf-8"), vars);
            return pom;
        } catch (Exception e) {
            throw new BaseException(e);
        }
    }

    /**
     * 生成model的java文件
     *
     * @param modelExt
     * @param basePackageName
     * @return
     */
    private String genBaseModelJavaFile(DataModelExt modelExt, String basePackageName) {
        Map<String, Object> vars = new HashMap();
        vars.put("model", modelExt);
        vars.put("basePackageName", basePackageName);
        try {
            String tpl = "myTemplates/gen/data-model.java.ftl";
            String pom = FtlUtils.render2(IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream(tpl), "utf-8"), vars);
            return pom;
        } catch (Exception e) {
            throw new BaseException(e);
        }
    }
}
