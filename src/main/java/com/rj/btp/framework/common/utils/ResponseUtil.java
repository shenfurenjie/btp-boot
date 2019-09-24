package com.rj.btp.framework.common.utils;


import com.baomidou.mybatisplus.core.toolkit.ObjectUtils;
import com.rj.btp.framework.common.constants.BaseConstant;
import com.rj.btp.framework.common.enums.ErrorCodeEnum;
import com.rj.btp.framework.common.wrapper.ResponseWrapper;
import com.rj.btp.framework.common.wrapper.RestResponse;
import lombok.AccessLevel;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


/**
 * ResponseUtil 请求工具类
 */
@NoArgsConstructor(access = AccessLevel.PRIVATE)
@Slf4j
public class ResponseUtil {

    /**
     * Portal输出json字符串
     *
     * @param response
     * @param obj      需要转换JSON的对象
     */
    public static void writeValueAsJson(HttpServletRequest request, ResponseWrapper response, Object obj) {
        LogUtil.printLog((Long) request.getAttribute(BaseConstant.API_BEGIN_TIME),
                TypeUtil.castToString(request.getAttribute(BaseConstant.API_UID)),
                request.getParameterMap(),
                RequestUtil.getRequestBody(request),
                (String) request.getAttribute(BaseConstant.API_REQURL),
                (String) request.getAttribute(BaseConstant.API_MAPPING),
                (String) request.getAttribute(BaseConstant.API_METHOD),
                IpUtils.getIpAddr(request),
                obj);
        if (ObjectUtils.isNotNull(response, obj)) {
            response.writeValueAsJson(obj);
        }
    }

    /**
     * 打印日志信息但是不输出到浏览器
     *
     * @param request
     * @param obj
     */
    public static void writeValueAsJson(HttpServletRequest request, Object obj) {
        writeValueAsJson(request, null, obj);
    }

    /**
     * 发送错误信息
     *
     * @param request
     * @param response
     * @param codeEnum
     */
    public static void sendFail(HttpServletRequest request, HttpServletResponse response, ErrorCodeEnum codeEnum) {
        sendFail(request, response, codeEnum, null);
    }


    /**
     * 发送错误信息
     *
     * @param request
     * @param response
     * @param errorCodeEnum
     */
    public static void sendFail(HttpServletRequest request, HttpServletResponse response, ErrorCodeEnum errorCodeEnum,
                                Exception exception) {
        writeValueAsJson(request, getWrapper(response, errorCodeEnum), RestResponse.failure(errorCodeEnum, exception));
    }


    /**
     * 获取Response
     *
     * @return
     */
    public static ResponseWrapper getWrapper(HttpServletResponse response, ErrorCodeEnum errorCodeEnum) {
        return new ResponseWrapper(response, errorCodeEnum);
    }
}
