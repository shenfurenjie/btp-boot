package com.rj.btp.framework.common.exception;

import com.rj.btp.framework.common.enums.ErrorCodeEnum;
import lombok.Data;

/**
 * <p>
 * BaseException异常类
 * </p>
 */
@Data
public class BaseException extends RuntimeException {

    private static final long serialVersionUID = 1L;
    Integer code;
    String message;

    public BaseException(String message) {
        super(message);
        this.message = message;
    }

    public BaseException(Integer code, String message) {
        super(message);
        this.code = code;
        this.message = message;
    }

    public BaseException(ErrorCodeEnum errorCodeEnum) {
        super(errorCodeEnum.message());
        this.code = errorCodeEnum.code();
        this.message = errorCodeEnum.message();
    }

    public BaseException(Throwable throwable) {
        super(throwable);
    }

    public BaseException(String message, Throwable throwable) {
        super(message, throwable);
    }

}
