package com.tiger.btp.framework.graphql.model;

import lombok.Getter;
import lombok.Setter;
import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @Author: TigerRen
 * @Date: 2019/12/17 9:31 AM
 */
@Slf4j
public class QueryResult<T> {

    @Setter
    @Getter
    List<T> records;
    @Setter
    @Getter
    Long totalCount;
    @Setter
    @Getter
    Integer current;
    @Setter
    @Getter
    Integer size;
    @Setter
    @Getter
    Long pages;
}
