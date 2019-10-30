package com.tiger.btp.framework.common.interceptor;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * 记录服务调用时间的拦截器
 */
@Slf4j
@Component
public class TimeInterceptor extends HandlerInterceptorAdapter {

    /**
     * 在Controller执行之前调用，如果返回false，controller不执行
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param handler             此参数记录了处理对象，包括类名和方法名等信息
     * @return
     * @throws Exception
     */
    @Override
    public boolean preHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object handler) {
        //设置开始时间
        httpServletRequest.setAttribute("startTime", System.currentTimeMillis());
        return true;
    }

    /**
     * controller执行之后，且页面渲染之前调用
     * 接口成功返回后，如果调用控制器方法时控制器方法抛出异常。则post方法不会被调用
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param modelAndView
     * @throws Exception
     */
    @Override
    public void postHandle(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, ModelAndView modelAndView) {
//        long startTime= (long) httpServletRequest.getAttribute("startTime");
//        log.error("TimeInterceptor耗时:"+(new Date().getTime()-startTime));
    }

    /**
     * 页面渲染之后调用，一般用于资源清理操作
     * 处理完成,无论控制器方法成功与否。都会进入这个方法
     *
     * @param httpServletRequest
     * @param httpServletResponse
     * @param o
     * @param e
     * @throws Exception，当控制器方法抛出异常时，此exception有值,如果有全局异常处理器（参考ControllerExceptionHandler）它将拿不到异常对象
     */
    @Override
    public void afterCompletion(HttpServletRequest httpServletRequest, HttpServletResponse httpServletResponse, Object o, Exception e) {
        long startTime = (long) httpServletRequest.getAttribute("startTime");
        long endTime = System.currentTimeMillis();
        long time = endTime - startTime;
        log.error("TimeInterceptor耗时（ms）:" + time);
    }
}
