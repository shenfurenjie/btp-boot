package com.tiger.btp.framework.common.constants;


/**
 * 系统常量
 */
public class BaseConstant {

    public static final String projectPath = System.getProperty("user.dir");

    public static final String javaPath = projectPath + "/src/main/java";

    public static final String resourcePath = projectPath + "/src/main/resources";


    public static final String mapperPath = projectPath + "/src/main/resources/mapper/";


    /**
     * 请求报文体
     */
    public static final String REQUEST_BODY = "requestBody";


    /**
     * 请求url
     */
    public static String API_REQURL = "API_REQURL";
    /**
     * 请求映射
     */
    public static String API_MAPPING = "API_MAPPING";

    /**
     * 请求方法
     */
    public static String API_METHOD = "API_METHOD";

    /**
     * 请求开始时间
     */
    public static String API_BEGIN_TIME = "API_BEGIN_TIME";

    /**
     * 请求线程ID
     */
    public static String API_UID = "API_UID";

    /**
     * 批量默认大小
     */
    public static int BATCH_SIZE = 1000;
}
