package com.tiger.btp.framework.common.utils;

/**
 * maven操作相关方法工具类
 */
public class MavenUtil {

    /**
     * 获取包名称，按照命名规则（字母、数字、下划线）
     *
     * @param groupId
     * @param artifactId
     * @return
     */
    public static String getPackageName(String groupId, String artifactId) {
        String packageName = groupId + "." + artifactId;
        packageName = packageName.replaceAll("-", "_");
        return packageName;
    }
}
