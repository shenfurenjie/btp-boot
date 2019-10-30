package com.tiger.btp.framework.common.utils;

import com.tiger.btp.framework.common.context.AppContextUtil;
import com.tiger.btp.framework.model.Log;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.Optional;

/**
 * 请求日志工具类
 *
 * @author Caratacus
 */
@Slf4j
public class LogUtil {

    /**
     * 获取日志对象
     *
     * @param beginTime
     * @param parameterMap
     * @param requestBody
     * @param url
     * @param mapping
     * @param method
     * @param ip
     * @param object
     * @return
     */
    public static void printLog(Long beginTime, String uid, Map<String, String[]> parameterMap, String requestBody, String url, String mapping, String method, String ip, Object object) {
        Log logInfo = Log.builder()
                //查询参数
                .parameterMap(parameterMap)
                .uid(uid)
                //请求体
                .requestBody(Optional.ofNullable(JacksonUtil.parse(requestBody)).orElse(requestBody))
                //请求路径
                .url(url)
                //请求mapping
                .mapping(mapping)
                //请求方法
                .method(method)
                .runTime((beginTime != null ? System.currentTimeMillis() - beginTime : 0) + "ms")
                .result(object)
                .ip(ip)
                .build();
        log.info(JacksonUtil.toJson(logInfo));
    }

    public static void doAfterReturning(Object ret) {
        ResponseUtil.writeValueAsJson(AppContextUtil.getRequest(), ret);
    }
}
