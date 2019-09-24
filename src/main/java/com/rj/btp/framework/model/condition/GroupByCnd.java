package com.rj.btp.framework.model.condition;

import com.rj.btp.framework.model.enums.StatisticsFunctionEnum;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 分组条件
 */
@Slf4j
@Data
public class GroupByCnd {

    /**
     * 列名称集合
     */
    String colName;

    /**
     * 统计函数
     */
    StatisticsFunctionEnum statisticsFun;

    /**
     * having条件
     */
    Object havingValue;


}
