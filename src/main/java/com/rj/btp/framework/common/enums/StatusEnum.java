package com.rj.btp.framework.common.enums;

/**
 * 状态枚举
 **/
public enum StatusEnum {

    NORMAL(0, "正常"), DISABLE(1, "禁止");

    private Integer code;
    private String value;

    StatusEnum(int code) {
        this.code = code;
    }

    StatusEnum(Integer code, String value) {
        this.code = code;
        this.value = value;
    }

    public Integer code() {
        return this.code;
    }

    public String value() {
        return this.value;
    }
}
