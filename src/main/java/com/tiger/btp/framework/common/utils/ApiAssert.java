package com.tiger.btp.framework.common.utils;

import com.tiger.btp.framework.common.enums.ErrorCodeEnum;
import com.tiger.btp.framework.common.exception.BaseException;

import java.util.Objects;

/**
 * <p>
 * API 断言
 * </p>
 **/
public class ApiAssert {


    /**
     * 失败结果
     *
     * @param errorCodeEnum 异常错误码
     */
    public static void failure(ErrorCodeEnum errorCodeEnum) {
        throw new BaseException(errorCodeEnum);
    }


    /**
     * obj1 eq obj2
     *
     * @param obj1
     * @param obj2
     * @param errorCodeEnum
     */
    public static void equals(ErrorCodeEnum errorCodeEnum, Object obj1, Object obj2) {
        if (!Objects.equals(obj1, obj2)) {
            failure(errorCodeEnum);
        }
    }

    public static void isTrue(ErrorCodeEnum errorCodeEnum, boolean condition) {
        if (!condition) {
            failure(errorCodeEnum);
        }
    }


}
