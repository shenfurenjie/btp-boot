package com.tiger.btp.framework;

import lombok.extern.slf4j.Slf4j;

import javax.tools.JavaCompiler;
import javax.tools.ToolProvider;
import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.jar.Attributes;
import java.util.jar.JarEntry;
import java.util.jar.JarOutputStream;
import java.util.jar.Manifest;

/**
 * Jar生成工具类
 */
@Slf4j
public class CompilerAndJarTools {

    public static void genMavenProject(String projectPath) {
        File projectDir = new File(projectPath);
        if (!projectDir.exists()) {
            projectDir.mkdirs();
            File srcDir = new File(projectPath + "/src");
            File mainDir = new File(projectPath + "/src/main");
            File mainJavaDir = new File(projectPath + "/src/main/java");
            File mainResourceDir = new File(projectPath + "/src/main/resources");
            File testDir = new File(projectPath + "/src/test");
            File testJavaDir = new File(projectPath + "/src/test/java");
            File testResourceDir = new File(projectPath + "/src/test/resources");
            File pomFile = new File(projectPath + "/pom.xml");
        }

    }

    /**
     * 编译
     *
     * @throws IOException
     */
    public static void complier(String javaClassPath, String javaSourcePath) {
        log.info("******开始编译java源代码******");
        File javaclassDir = new File(javaClassPath);
        if (!javaclassDir.exists()) {
            javaclassDir.mkdirs();
        }
        List<String> javaSourceList = new ArrayList<String>();
        getFileList(new File(javaSourcePath), javaSourceList);

        JavaCompiler javaCompiler = ToolProvider.getSystemJavaCompiler();
        int result = -1;
        for (int i = 0; i < javaSourceList.size(); i++) {
            result = javaCompiler.run(null, null, null, "-d", javaClassPath, javaSourceList.get(i));
            System.out.println(result == 0 ? "*** 编译成功 : " + javaSourceList.get(i) : "### 编译失败 : " + javaSourceList.get(i));
        }
        log.info("******java源代码编译完成******");
    }

    private static void getFileList(File file, List<String> fileList) {
        if (file.isDirectory()) {
            File[] files = file.listFiles();
            for (int i = 0; i < files.length; i++) {
                if (files[i].isDirectory()) {
                    getFileList(files[i], fileList);
                } else {
                    fileList.add(files[i].getPath());
                }
            }
        }
    }


    /**
     * 打包
     *
     * @throws FileNotFoundException
     * @throws IOException
     */
    public static void generateJar(String javaClassPath, String targetPath) throws IOException {
        log.info("******开始生成jar包******");
        String targetDirPath = targetPath.substring(0, targetPath.lastIndexOf("/"));
        File targetDir = new File(targetDirPath);
        if (!targetDir.exists()) {
            targetDir.mkdirs();
        }

        Manifest manifest = new Manifest();
        manifest.getMainAttributes().put(Attributes.Name.MANIFEST_VERSION, "1.0");

        JarOutputStream target = new JarOutputStream(new FileOutputStream(targetPath), manifest);
        writeClassFile(new File(javaClassPath), target, javaClassPath);
        target.close();
        log.info("******jar包生成完毕******");
    }

    private static void writeClassFile(File source, JarOutputStream target, String javaClassPath) throws IOException {
        BufferedInputStream in = null;
        try {
            if (source.isDirectory()) {
                String name = source.getPath().replace("\\", "/");
                if (!name.isEmpty()) {
                    if (!name.endsWith("/")) {
                        name += "/";
                    }
                    name = name.substring(javaClassPath.length());
                    JarEntry entry = new JarEntry(name);
                    entry.setTime(source.lastModified());
                    target.putNextEntry(entry);
                    target.closeEntry();
                }
                for (File nestedFile : source.listFiles())
                    writeClassFile(nestedFile, target, javaClassPath);
                return;
            }

            String middleName = source.getPath().replace("\\", "/").substring(javaClassPath.length());
            JarEntry entry = new JarEntry(middleName);
            entry.setTime(source.lastModified());
            target.putNextEntry(entry);
            in = new BufferedInputStream(new FileInputStream(source));

            byte[] buffer = new byte[1024];
            while (true) {
                int count = in.read(buffer);
                if (count == -1)
                    break;
                target.write(buffer, 0, count);
            }
            target.closeEntry();
        } finally {
            if (in != null)
                in.close();
        }
    }

    public static void main(String[] args) throws IOException {
        String currentDir = "/Users/renjie/fileDir/myProject";
        String javaSourcePath = currentDir + "/src/main/java/";
        String javaClassPath = currentDir + "/target/classes";
        String targetPath = currentDir + "/target/MyProject.jar";

        complier(javaClassPath, javaSourcePath);
        generateJar(javaClassPath, targetPath);
    }

}
