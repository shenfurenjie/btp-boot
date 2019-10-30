package com.tiger.btp.framework.id;

public class SnowflakeIdHelper {

    public static SnowflakeIdWorker snowflakeIdWorker;

    public static Long getId() {
        if (snowflakeIdWorker == null) {
            snowflakeIdWorker = new SnowflakeIdWorker(0, 0);
        }
        return snowflakeIdWorker.nextId();
    }
}
