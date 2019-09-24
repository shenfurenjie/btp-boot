package com.rj.btp.framework.common.wrapper;

import com.rj.btp.framework.common.enums.ErrorCodeEnum;
import lombok.Data;

import java.io.Serializable;

/**
 * 返回结果封装
 */
@Data
public class RestResponse implements Serializable {


    private Integer code;

    private String message;

    private Object data;

    public RestResponse() {

    }

    public RestResponse(Object data) {
        this.data = data;
    }

    public RestResponse(int code, String msg) {
        this.code = code;
        this.message = msg;
    }

    public RestResponse(int code, Object data) {
        this.code = code;
        this.data = data;
    }

    public static RestResponse success() {
        RestResponse restResponse = new RestResponse();
        restResponse.setCode(ErrorCodeEnum.SUCCESS.code());
        restResponse.setMessage(ErrorCodeEnum.SUCCESS.message());
        return restResponse;
    }

    public static RestResponse success(Object data) {
        RestResponse restResponse = new RestResponse();
        restResponse.setCode(ErrorCodeEnum.SUCCESS.code());
        restResponse.setMessage(ErrorCodeEnum.SUCCESS.message());
        restResponse.setData(data);
        return restResponse;
    }

    public static RestResponse failure() {
        RestResponse restResponse = new RestResponse();
        restResponse.setCode(ErrorCodeEnum.FAIL.code());
        return restResponse;
    }

    public static RestResponse failure(Integer code) {
        RestResponse restResponse = new RestResponse();
        restResponse.setCode(code);
        return restResponse;
    }

    public static RestResponse failure(String message) {
        RestResponse restResponse = new RestResponse();
        restResponse.setMessage(message);
        return restResponse;
    }

    public static RestResponse failure(Integer code, String message) {
        RestResponse restResponse = new RestResponse();
        restResponse.setCode(code);
        restResponse.setMessage(message);
        return restResponse;
    }

    public static RestResponse failure(Integer code, String message, Object data) {
        RestResponse restResponse = new RestResponse();
        restResponse.setCode(code);
        restResponse.setMessage(message);
        restResponse.setData(data);
        return restResponse;
    }

    public static RestResponse failure(ErrorCodeEnum errorCodeEnum) {
        RestResponse restResponse = new RestResponse();
        restResponse.setCode(errorCodeEnum.code());
        restResponse.setMessage(errorCodeEnum.message());
        return restResponse;
    }

    public static RestResponse failure(ErrorCodeEnum errorCodeEnum, Object data) {
        RestResponse restResponse = new RestResponse();
        restResponse.setCode(errorCodeEnum.code());
        restResponse.setMessage(errorCodeEnum.message());
        restResponse.setData(data);
        return restResponse;
    }

}
