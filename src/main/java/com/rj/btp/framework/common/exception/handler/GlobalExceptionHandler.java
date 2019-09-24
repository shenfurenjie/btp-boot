package com.rj.btp.framework.common.exception.handler;

import com.rj.btp.framework.common.enums.ErrorCodeEnum;
import com.rj.btp.framework.common.exception.BaseException;
import com.rj.btp.framework.common.wrapper.RestResponse;
import lombok.extern.slf4j.Slf4j;
import org.apache.shiro.ShiroException;
import org.apache.shiro.authz.UnauthenticatedException;
import org.apache.shiro.authz.UnauthorizedException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletRequest;

/**
 * 全局controller异常处理
 */
@Slf4j
@ControllerAdvice
public class GlobalExceptionHandler {


    public static final String DEFAULT_ERROR_VIEW = "error";

    @ExceptionHandler(value = Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
        ModelAndView mav = new ModelAndView();
        mav.addObject("exception", e);
        mav.addObject("url", req.getRequestURL());
        mav.setViewName(DEFAULT_ERROR_VIEW);
        return mav;
    }

    @ExceptionHandler(value = RuntimeException.class)
    @ResponseBody
    public RestResponse jsonErrorHandler(HttpServletRequest req, Exception e) {
        if (e instanceof BaseException) {
            BaseException baseException = (BaseException) e;
            return RestResponse.failure(((BaseException) e).getCode(), e.getMessage());
        } else {
            log.error("【系统异常】{}", e);
            return RestResponse.failure(ErrorCodeEnum.FAIL.code(), ErrorCodeEnum.FAIL.message());
        }
    }

    @ExceptionHandler(ShiroException.class)
    @ResponseBody
    public RestResponse handleShiroException(ShiroException e) {
        String eName = e.getClass().getSimpleName();
        log.error("shiro执行出错：{}", eName);
        return RestResponse.failure("鉴权或授权过程出错");
    }

    @ExceptionHandler(UnauthenticatedException.class)
    @ResponseBody
    public RestResponse page401(UnauthenticatedException e) {
        String eMsg = e.getMessage();
        return RestResponse.failure("用户未登录");
    }

    @ExceptionHandler(UnauthorizedException.class)
    @ResponseBody
    public RestResponse page403() {
        return RestResponse.failure("用户没有访问权限");
    }
}
