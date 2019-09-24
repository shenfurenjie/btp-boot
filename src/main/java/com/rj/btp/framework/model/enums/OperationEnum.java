package com.rj.btp.framework.model.enums;

public enum OperationEnum {
    /**
     * 等于 ==
     */
    eq,
    /**
     * 不等于 <>
     */
    ne,
    /**
     * 大于 >
     */
    gt,
    /**
     * 大于等于 >=
     */
    ge,
    /**
     * 小于 <
     */
    lt,

    /**
     * 小于 <=
     */
    le,
    /**
     * 区间 BETWEEN 值1 AND 值2
     */
    between,

    /**
     * 区间 NOT BETWEEN 值1 AND 值2
     */
    notBetween,
    /**
     * LIKE '%值%'
     */
    like,

    /**
     * NOT LIKE '%值%'
     */
    notLike,
    /**
     * LIKE '%值'
     */
    likeLeft,
    /**
     * LIKE '值%'
     */
    likeRight,

    /**
     * 字段 IS NULL
     */
    isNull,
    /**
     * 字段 IS NOT NULL
     */
    isNotNull,

    /**
     * 字段 IN (value.get(0), value.get(1), ...)
     */
    in,
    /**
     * 字段 NOT IN (value.get(0), value.get(1), ...)
     */
    notIn,

    /**
     * 字段 IN ( sql语句 )
     */
    inSql,
    /**
     * 字段 NOT IN ( sql语句 )
     */
    notInSql,
    /**
     * 分组：GROUP BY 字段,...
     * 例: groupBy("id", "name")--->group by id,name
     */
    groupBy,

    /**
     * 排序：ORDER BY 字段, ... ASC
     * 例: orderByAsc("id", "name")--->order by id ASC,name ASC
     */
    orderByAsc,
    /**
     * 排序：ORDER BY 字段, ... DESC
     * 例: orderByDesc("id", "name")--->order by id DESC,name DESC
     */
    orderByDesc,

    /**
     * HAVING ( sql语句 )
     * having("sum(age) > 10")--->having sum(age) > 10
     */
    having,

    /**
     * 拼接 EXISTS ( sql语句 )
     * 例: exists("select id from table where age = 1")--->exists (select id from table where age = 1)
     */
    exists,

    /**
     * 拼接 NOT EXISTS ( sql语句 )
     * 例: notExists("select id from table where age = 1")--->notExists (select id from table where age = 1)
     */
    notExists

}
