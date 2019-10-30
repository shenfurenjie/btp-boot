package com.tiger.btp.framework.model.condition;

import lombok.Data;
import lombok.NonNull;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


/**
 * where的逻辑运算 and  or
 */
@Slf4j
@Data
public class RelCnd {


    @NonNull
    private String key;

    private List<FieldWrapper> fieldArray;

    private List<RelCnd> childCndArray;
}
