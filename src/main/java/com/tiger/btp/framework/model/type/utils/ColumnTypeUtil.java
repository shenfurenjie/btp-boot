package com.tiger.btp.framework.model.type.utils;

import com.tiger.btp.framework.model.type.ColumnType;

import java.math.BigDecimal;
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ColumnTypeUtil {


    private static Map<ColumnType, Integer> jdbcTypeMapping = new HashMap<ColumnType, Integer>();

    /**
     * 数据库类型-java类型
     */
    private static Map<ColumnType, Class<?>> javaClassMapping = new HashMap<>();
    /**
     * 数据库类型-hibernate类型
     */
    private static Map<ColumnType, String> hibernateMapping = new HashMap<ColumnType, String>();

    static {
        jdbcTypeMapping.put(ColumnType.INTEGER, Types.INTEGER);
        jdbcTypeMapping.put(ColumnType.LONG, Types.BIGINT);
        jdbcTypeMapping.put(ColumnType.BOOLEAN, Types.INTEGER);
        jdbcTypeMapping.put(ColumnType.STRING, Types.VARCHAR);
        jdbcTypeMapping.put(ColumnType.TIMESTAMP, Types.TIMESTAMP);
        jdbcTypeMapping.put(ColumnType.BIG_DECIMAL, Types.NUMERIC);
        jdbcTypeMapping.put(ColumnType.CLOB, Types.CLOB);
        jdbcTypeMapping.put(ColumnType.BLOB, Types.BLOB);
        jdbcTypeMapping.put(ColumnType.DTAE, Types.DATE);
    }

    static {
        javaClassMapping.put(ColumnType.STRING, String.class);
        javaClassMapping.put(ColumnType.BIG_DECIMAL, BigDecimal.class);
        javaClassMapping.put(ColumnType.INTEGER, Integer.class);
        javaClassMapping.put(ColumnType.LONG, Long.class);
        javaClassMapping.put(ColumnType.BOOLEAN, Boolean.class);
        javaClassMapping.put(ColumnType.TIMESTAMP, Date.class);
        javaClassMapping.put(ColumnType.BLOB, Byte[].class);
        javaClassMapping.put(ColumnType.DTAE, Date.class);
    }

    static {
        hibernateMapping.put(ColumnType.STRING, ColumnType.STRING.name().toLowerCase());
        hibernateMapping.put(ColumnType.BIG_DECIMAL, ColumnType.BIG_DECIMAL.name().toLowerCase());
        hibernateMapping.put(ColumnType.INTEGER, ColumnType.INTEGER.name().toLowerCase());
        hibernateMapping.put(ColumnType.LONG, ColumnType.LONG.name().toLowerCase());
        hibernateMapping.put(ColumnType.BOOLEAN, ColumnType.BOOLEAN.name().toLowerCase());
        hibernateMapping.put(ColumnType.TIMESTAMP, ColumnType.TIMESTAMP.name().toLowerCase());
        hibernateMapping.put(ColumnType.BLOB, ColumnType.BLOB.name().toLowerCase());
        hibernateMapping.put(ColumnType.DTAE, ColumnType.DTAE.name().toLowerCase());
    }

    public static Integer getJdbcType(ColumnType type) {
        return jdbcTypeMapping.get(type);
    }

    /**
     * 获取POJO的类型
     *
     * @param type
     * @return
     */
    public static String getHbmType(ColumnType type) {
        return hibernateMapping.get(type);
    }

    /**
     * 获取POJO的类型
     *
     * @param type
     * @return
     */
    public static Class<?> getJavaClass(ColumnType type) {
        return javaClassMapping.get(type);
    }
}
