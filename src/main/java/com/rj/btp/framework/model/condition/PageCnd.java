package com.rj.btp.framework.model.condition;


import lombok.Data;
import lombok.extern.slf4j.Slf4j;

/**
 * 分页条件对象
 */
@Slf4j
@Data
public class PageCnd {

    /**
     * 每页显示条数，默认 10
     */
    int size = 10;
    /**
     * 当前页
     */
    int current = 0;

//    /**
//     * 排序字段
//     */
//    List<SortCnd> sortCndArray;
}
