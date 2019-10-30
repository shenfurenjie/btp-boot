package com.tiger.btp.framework.common.utils;

import com.tiger.btp.framework.common.constants.BaseConstant;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.core.NamedThreadLocal;
import org.springframework.web.util.WebUtils;

import javax.servlet.ServletRequest;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServletRequest;
import java.io.BufferedReader;
import java.util.Collections;
import java.util.Map;

/**
 * Web层辅助类
 */
@Slf4j
public final class WebUtil {
    public static ThreadLocal<HttpServletRequest> REQUEST = new NamedThreadLocal<HttpServletRequest>(
            "ThreadLocalRequest");

    private WebUtil() {
    }

    /**
     * 获取指定Cookie的值
     *
     * @param request
     * @param cookieName   cookie名字
     * @param defaultValue 缺省值
     * @return
     */
    public static final String getCookieValue(HttpServletRequest request, String cookieName, String defaultValue) {
        Cookie cookie = WebUtils.getCookie(request, cookieName);
        if (cookie == null) {
            return defaultValue;
        }
        return cookie.getValue();
    }


    /**
     * 获得参数Map
     *
     * @param request
     * @return
     */
    public static final Map<String, Object> getParameterMap(HttpServletRequest request) {
        return WebUtils.getParametersStartingWith(request, null);
    }

    public static String getRequestBody(ServletRequest request) {
        String str, body = (String) request.getAttribute(BaseConstant.REQUEST_BODY);
        if (StringUtils.isEmpty(body)) {
            body = "";
            try {
                BufferedReader br = request.getReader();
                while ((str = br.readLine()) != null) {
                    body += str;
                }
                log.info("request body===>{}", body);
                request.setAttribute(BaseConstant.REQUEST_BODY, body);
            } catch (Exception e) {
                return null;
            }
        }
        return body;
    }

    public static Map<String, Object> getRequestParam(String param) {
        Map<String, Object> paramMap = Collections.emptyMap();
        if (null != param) {
            String[] params = param.split("&");
            for (String param2 : params) {
                String[] p = param2.split("=");
                if (p.length == 2) {
                    paramMap.put(p[0], p[1]);
                }
            }
        }
        return paramMap;
    }


}
