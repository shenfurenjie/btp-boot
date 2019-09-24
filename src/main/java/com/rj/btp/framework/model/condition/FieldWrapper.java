package com.rj.btp.framework.model.condition;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 条件field
 */
@Slf4j
@Data
public class FieldWrapper {

    private String fieldKey;

    private Object fieldValue;

    private String operation;


}
