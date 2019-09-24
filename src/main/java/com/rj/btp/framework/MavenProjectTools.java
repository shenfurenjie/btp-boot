package com.rj.btp.framework;

import org.apache.maven.shared.invoker.*;

import java.io.File;
import java.io.IOException;
import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public class MavenProjectTools {

    public static void hello() throws MavenInvocationException {
        InvocationRequest request = new DefaultInvocationRequest();
        request.setPomFile(new File("/Users/renjie/fileDir/to/pom.xml"));
        request.setGoals(Arrays.asList("clean", "install"));

        Invoker invoker = new DefaultInvoker();
        invoker.setMavenHome(new File("/Users/renjie/workspace/apache-maven-3.5.2/"));
        InvocationResult result = invoker.execute(request);

        if (result.getExitCode() != 0) {
            throw new IllegalStateException("Build failed.");
        }
    }

    public static void genMavenProject(String projectPath) throws IOException {
        File projectDir = new File(projectPath);
        if (!projectDir.exists()) {
            File srcDir = new File(projectPath + "/src");
            File mainDir = new File(projectPath + "/src/main");
            File mainJavaDir = new File(projectPath + "/src/main/java");
            File mainResourceDir = new File(projectPath + "/src/main/resources");
            File testDir = new File(projectPath + "/src/test");
            File testJavaDir = new File(projectPath + "/src/test/java");
            File testResourceDir = new File(projectPath + "/src/test/resources");
            File pomFile = new File(projectPath + "/pom.xml");

            projectDir.mkdirs();
            srcDir.mkdirs();
            mainDir.mkdirs();
            mainJavaDir.mkdirs();
            mainResourceDir.mkdirs();
            testDir.mkdirs();
            testJavaDir.mkdirs();
            testResourceDir.mkdirs();
            pomFile.createNewFile();
        }

    }

    public static void main(String[] args) throws IOException {
        genMavenProject("/Users/renjie/fileDir/myPrj");
        Map paramMap = new HashMap();
        paramMap.put("groupId", "Velocity");
        paramMap.put("artifactId", "Velocity");
        paramMap.put("version", "Velocity");
        paramMap.put("projectName", "Velocity");
        paramMap.put("description", "Velocity");
        //String writer = TemplateEngineTools.genRealFile(paramMap,"pom.xml.vm");
        File pomFile = new File("/Users/renjie/fileDir/myPrj/pom.xml");
        //FileUtils.writeStringToFile(pomFile,writer,"utf-8");
    }
}
