package com.tiger.btp.framework.common.utils;

import com.alibaba.fastjson.JSON;
import com.tiger.btp.framework.common.constants.ReqParamConstant;
import lombok.extern.slf4j.Slf4j;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 请求入参解析工具
 */
@Slf4j
public class ReqParamUtil {

    /**
     * 获取where结构体
     *
     * @param args
     * @return
     */
    public static Map<String, Object> getWhere(Map<String, Object> args) {
        Map<String, Object> where = new HashMap<>();
        if (args.containsKey(ReqParamConstant.WHERE) && args.get(ReqParamConstant.WHERE) != null) {
            where = (Map<String, Object>) args.get(ReqParamConstant.WHERE);
        }
        log.info("where : {}", where);
        return where;
    }

    public static void main(String[] args) {
        String s1 = "{\n" +
                "  \"where\": {\n" +
                "    \"id\": {\n" +
                "\t\t\t\"_eq\": \"1\"\n" +
                "    },\n" +
                "    \"_or\": {\n" +
                "      \"studentId\": {\n" +
                "        \"_eq\": 1\n" +
                "      },\n" +
                "      \"_and\": {\n" +
                "        \"courseId\": {\n" +
                "          \"_eq\": 1\n" +
                "        }\n" +
                "      }\n" +
                "    }\n" +
                "  }\n" +
                "}";

        Map whereMap = getWhere((Map<String, Object>) JSON.parse(s1));
    }

    /**
     * 获取orderBy结构体
     *
     * @param args
     * @return
     */
    protected List<Map<String, Object>> getOrderBy(Map<String, Object> args) {
        return (List<Map<String, Object>>) args.get(ReqParamConstant.ORDER_BY);
    }

    /**
     * 获取groupBy结构体
     *
     * @param args
     * @return
     */
    protected List<String> getGroupBy(Map<String, Object> args) {
        return (List<String>) args.get(ReqParamConstant.GROUP_BY);
    }
}
