package com.tiger.btp.framework.model.condition;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.List;


/**
 * 查询条件model
 */
@Slf4j
@Data
public class QueryCondition {

    /**
     * where条件
     */
    List<RelCnd> relCndArray;

    /**
     * 排序条件
     */
    List<SortCnd> sortCndArray;

    /**
     * 分页
     */
    PageCnd pageCnd;

    /**
     * 分组条件
     */
    List<GroupByCnd> groupByCndArray;
}
