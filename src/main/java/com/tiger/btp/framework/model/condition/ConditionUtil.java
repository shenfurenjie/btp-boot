package com.tiger.btp.framework.model.condition;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;

/**
 * 入参条件构造工具类
 */
@Slf4j
public class ConditionUtil {

    /**
     * 构建接口入参QueryCondition
     *
     * @param queryCndMap
     * @return
     */
    public static QueryCondition buildQueryCondition(Map<String, Object> queryCndMap) {
        String jsonString = JSON.toJSONString(queryCndMap);
        QueryCondition queryCondition = JSONObject.parseObject(jsonString, QueryCondition.class);
        return queryCondition;
    }

    /**
     * 构建接口入参QueryCondition
     *
     * @param queryCndJson
     * @return
     */
    public static QueryCondition buildQueryCondition(String queryCndJson) {
        QueryCondition queryCondition = JSONObject.parseObject(queryCndJson, QueryCondition.class);
        return queryCondition;
    }


    /**
     * 构建接口入参
     *
     * @param updateCndMap
     * @return
     */
    public static UpdateCondition buildUpdateCondition(Map<String, Object> updateCndMap) {
        String jsonString = JSON.toJSONString(updateCndMap);
        UpdateCondition updateCondition = JSONObject.parseObject(jsonString, UpdateCondition.class);
        return updateCondition;
    }

    /**
     * 构建接口入参
     *
     * @param updateCndJson
     * @return
     */
    public static UpdateCondition buildUpdateCondition(String updateCndJson) {
        UpdateCondition updateCondition = JSONObject.parseObject(updateCndJson, UpdateCondition.class);
        return updateCondition;
    }


    public static void main(String[] args) {
        String json = "{\n" +
                "\t\"relCndArray\": [{\n" +
                "\t\t\t\"key\": \"and\",\n" +
                "\t\t\t\"fieldArray\": [{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"eq\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"Tom\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"eq\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"Jack\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"key\": \"or\",\n" +
                "\t\t\t\"fieldArray\": [{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"eq\",\n" +
                "\t\t\t\t\t\"paramValue\": \"Tom\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"eq\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"Jack\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

        String json1 = "{\n" +
                "\t\"relCndArray\": [{\n" +
                "\t\t\t\"key\": \"and\",\n" +
                "\t\t\t\"fieldArray\": [{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"eq\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"tom\"\n" +
                "\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"ne\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"jack\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"gt\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"jack\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"ge\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"jack\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"lt\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"jack\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"le\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"jack\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"key\": \"or\",\n" +
                "\t\t\t\"fieldArray\": [{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"like\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"tom\"\n" +
                "\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"likeLeft\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"jack\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"likeRight\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"jack\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"key\": \"or\",\n" +
                "\t\t\t\"fieldArray\": [{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"in\",\n" +
                "\t\t\t\t\t\"fieldValue\": [\n" +
                "\t\t\t\t\t\t\"tom\", \"jcack\"\n" +
                "\t\t\t\t\t]\n" +
                "\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"notIn\",\n" +
                "\t\t\t\t\t\"fieldValue\": [\n" +
                "\t\t\t\t\t\t\"tom\", \"jcack\"\n" +
                "\t\t\t\t\t]\n" +
                "\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"key\": \"and\",\n" +
                "\t\t\t\"fieldArray\": [{\n" +
                "\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\"operation\": \"isNull\"\n" +
                "\n" +
                "\t\t\t}]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"key\": \"or\",\n" +
                "\t\t\t\"fieldArray\": [{\n" +
                "\t\t\t\t\"fieldKey\": \"id\",\n" +
                "\t\t\t\t\"operation\": \"between\",\n" +
                "\t\t\t\t\"fieldValue\": [\n" +
                "\t\t\t\t\t1, 2\n" +
                "\t\t\t\t]\n" +
                "\n" +
                "\t\t\t}]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"key\": \"and\",\n" +
                "\t\t\t\"fieldArray\": [{\n" +
                "\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\"operation\": \"isNull\"\n" +
                "\n" +
                "\t\t\t}]\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"sortCndArray\": [{\n" +
                "\t\t\t\"colName\": \"name\",\n" +
                "\t\t\t\"order\": \"asc\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"colName\": \"id\",\n" +
                "\t\t\t\"order\": \"desc\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

        String json3 = "";
        //QueryWrapper qWrapper = buildQueryWrapper(json1);
        QueryCondition queryCondition = JSONObject.parseObject(json, QueryCondition.class);
        //ConditionUtil.buildQueryWrapper(json);
    }
}
