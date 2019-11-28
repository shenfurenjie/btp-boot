package com.tiger.btp.app.service;

import com.tiger.btp.app.AppService;
import com.tiger.btp.app.AppServiceFactory;
import com.tiger.btp.app.DataModelExt;
import com.tiger.btp.framework.common.constants.BaseConstant;
import com.tiger.btp.framework.common.exception.BaseException;
import com.tiger.btp.framework.common.utils.FtlUtils;
import com.tiger.btp.framework.common.utils.MavenUtil;
import com.tiger.btp.framework.maven.PomXml;
import com.tiger.btp.model.data_model.ValGroup;
import com.tiger.btp.model.data_model.ValGroups;
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
 * 根据DataModel配置生成POJO
 */
@Slf4j
@Service
public class GenModelService {

    @Autowired
    AppServiceFactory appServiceFactory;

    /**
     * 生成Pojo工程推送到Maven远程仓库中
     *
     * @param pomXml
     * @param mavenHomePath
     * @throws Exception
     */
    public void genPojoProjectAndPublishToMavenRepo(PomXml pomXml, String mavenHomePath) throws Exception {
        AppService appService = appServiceFactory.getAppService(pomXml.getName());
        if (appService == null) {
            throw new BaseException("no available app exist!");
        }
        String fileName = BaseConstant.tmpdir.getPath() + "/" + UUID.randomUUID().toString();
        File tmpFile = new File(fileName);
        try {
            genPojoProject(tmpFile, pomXml);
            InvocationRequest request = new DefaultInvocationRequest();
            request.setPomFile(new File(tmpFile.getPath() + "/pom.xml"));
            request.setGoals(Collections.singletonList("clean deploy  -Dmaven.test.skip=true"));
            Invoker invoker = new DefaultInvoker();
            invoker.setMavenHome(new File(mavenHomePath));
            InvocationResult result = invoker.execute(request);
            if (result.getExitCode() != 0) {
                throw new IllegalStateException("Build failed.");
            }
        } finally {
            FileUtils.forceDelete(tmpFile);
        }

    }

    /**
     * 生成工程文件
     *
     * @param dest
     * @param pomXml
     * @throws IOException
     */
    public void genPojoProject(File dest, PomXml pomXml) throws IOException {
        if (dest == null) {
            throw new BaseException("dest file not allow null!");
        } else {
            if (!dest.exists()) {
                FileUtils.forceMkdir(dest);
            }
            File javaFile = new File(dest.getPath() + "/src/main/java");
            FileUtils.forceMkdir(javaFile);
            FileUtils.forceMkdir(new File(dest.getPath() + "/src/test/java"));
            String pom = genPojoProjectPomXml(pomXml);
            FileUtils.writeStringToFile(new File(dest.getPath() + "/pom.xml"), pom, "utf-8");
            String modelId;
            String packageName;
            String fileName;

            AppService appService = appServiceFactory.getAppService(pomXml.getName());
            Map<String, DataModelExt> dataModelExtMap = appService.getDataModelFactory().getDataModelExtMap();
            Iterator iterator = dataModelExtMap.keySet().iterator();
            while (iterator.hasNext()) {
                modelId = (String) iterator.next();
                DataModelExt ext = dataModelExtMap.get(modelId);
                ext.setDataModelFactory(appService.getDataModelFactory());
                packageName = MavenUtil.getPackageName(pomXml.getGroupId(), pomXml.getArtifactId());
                fileName = packageName.replace("\\.", "/");
                File packageDir = new File(FilenameUtils.concat(javaFile.getPath(), fileName));
                if (!packageDir.exists()) {
                    FileUtils.forceMkdir(packageDir);
                }
                String base = genBasePojoJavaFile(ext, packageName);
                FileUtils.writeStringToFile(new File(packageDir.getPath() + "/" + ext.getBaseClassName() + ".java"), base, "utf-8");
                String clazz = this.genPojoJavaFile(ext, packageName);
                FileUtils.writeStringToFile(new File(packageDir.getPath() + "/" + ext.getClassName() + ".java"), clazz, "utf-8");
            }

        }
    }

    private String genBasePojoJavaFile(DataModelExt modelExt, String packageName) {
        Map<String, Object> vars = new HashMap();
        ValGroups valGroups = modelExt.getValGroups();
        vars.put("model", modelExt);
        vars.put("packageName", packageName);
        if (valGroups == null) {
            vars.put("valGroups", null);
        } else {
            List<String> groups = new ArrayList();
            Iterator var8 = valGroups.getValGroup().iterator();
            while (var8.hasNext()) {
                ValGroup group = (ValGroup) var8.next();
                groups.add(group.getName());
            }
            vars.put("valGroups", groups);
        }
        try {
            String tpl = "myTemplates/gen/data-model-base.java.ftl";
            String pom = FtlUtils.render2(IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream(tpl), "utf-8"), vars);
            return pom;
        } catch (Exception e) {
            throw new BaseException(e);
        }
    }

    public String genPojoJavaFile(DataModelExt modelExt, String packageName) {
        Map<String, Object> vars = new HashMap();
        vars.put("model", modelExt);
        vars.put("packageName", packageName);
        try {
            String pom = FtlUtils.render2(IOUtils.toString(this.getClass().getClassLoader().getResourceAsStream("myTemplates/gen/data-model.java.ftl"), "utf-8"), vars);
            return pom;
        } catch (Exception var5) {
            throw new BaseException(var5);
        }
    }

    /**
     * 生成pom文件
     *
     * @param pomXml
     * @return
     */
    public String genPojoProjectPomXml(PomXml pomXml) {
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


}
