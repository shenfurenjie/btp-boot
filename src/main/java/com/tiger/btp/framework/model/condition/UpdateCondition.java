package com.tiger.btp.framework.model.condition;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;
import java.util.Map;

/**
 * 更新操作条件结构体
 */
@Slf4j
@Data
public class UpdateCondition {

    /**
     * where条件
     */
    List<RelCnd> relCndArray;

    /**
     * SET
     */
    List<Map<String, Object>> setCndArray;
}
