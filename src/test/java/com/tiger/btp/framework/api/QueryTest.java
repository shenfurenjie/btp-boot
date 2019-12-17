package com.tiger.btp.framework.api;

import com.alibaba.fastjson.JSON;
import com.tiger.btp.framework.graphql.GraphQLProvider;
import graphql.ExecutionInput;
import graphql.ExecutionResult;
import lombok.extern.slf4j.Slf4j;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author: TigerRen
 * @Date: 2019/12/16 1:26 PM
 */
@Slf4j
@RunWith(SpringRunner.class)
@SpringBootTest
public class QueryTest {

    @Autowired
    GraphQLProvider graphQLProvider;

    //简单条件查询
    @Test
    public void testQuery1() {

        String queryStr = "query ($queryCondition: QueryCondition) {\n" +
                "  query_school(queryCondition: $queryCondition) {\n" +
                "    id\n" +
                "    schoolName\n" +
                "  }\n" +
                "}";

        String cndStr = "{\n" +
                "\t\"relCndArray\": [{\n" +
                "\t\t\"key\": \"and\",\n" +
                "\t\t\"fieldArray\": [{\n" +
                "\t\t\t\t\"fieldKey\": \"id\",\n" +
                "\t\t\t\t\"operation\": \"eq\",\n" +
                "\t\t\t\t\"fieldValue\": 1\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t}]\n" +
                "}";
        ExecutionInput.Builder builder = ExecutionInput.newExecutionInput();
        builder.query(queryStr);

        Map param = new HashMap();
        param.put("queryCondition", JSON.parse(cndStr));
        builder.variables(param);
        ExecutionResult result = graphQLProvider.graphQL().execute(builder);
        log.info("testQuery1 {}", result);

    }

    @Test
    public void testQuery2() {

        String queryStr = "query query_school_paged ($queryCondition: QueryCondition) {\n" +
                "  query_school_paged(queryCondition: $queryCondition) {\n" +
                "    records{\n" +
                "      id\n" +
                "      schoolName\n" +
                "    }\n" +
                "    totalCount\n" +
                "    size\n" +
                "    current\n" +
                "  }\n" +
                "}";

        String cndStr = "{\n" +
                "\t\"pageCnd\": {\n" +
                "\t\t\"size\": 2,\n" +
                "\t\t\"current\": 1\n" +
                "\t}\n" +
                "}";
        ExecutionInput.Builder builder = ExecutionInput.newExecutionInput();
        builder.query(queryStr);
        builder.operationName("query_school_paged");

        Map param = new HashMap();
        param.put("queryCondition", JSON.parse(cndStr));
        builder.variables(param);
        ExecutionResult result = graphQLProvider.graphQL().execute(builder);
        log.info("testQuery2 {}", result);

    }


    @Test
    public void testQuery3() {
        String queryStr = "query query_school($queryCondition: QueryCondition) {\n" +
                "  query_school(queryCondition: $queryCondition) {\n" +
                "    id\n" +
                "    schoolName\n" +
                "  }\n" +
                "}\n";

        String cndStr = "{\n" +
                "\t\"sortCndArray\": [{\n" +
                "\t\t\"colName\": \"schoolName\",\n" +
                "\t\t\"order\": \"desc\"\n" +
                "\t}]\n" +
                "}";
        ExecutionInput.Builder builder = ExecutionInput.newExecutionInput();
        builder.query(queryStr);
        builder.operationName("query_school");

        Map param = new HashMap();
        param.put("queryCondition", JSON.parse(cndStr));
        builder.variables(param);
        ExecutionResult result = graphQLProvider.graphQL().execute(builder);
        log.info("testQuery3 {}", result);

    }


    @Test
    public void testQuery4() {
        String queryStr = "query query_school($queryCondition: QueryCondition) {\n" +
                "  query_school(queryCondition: $queryCondition) {\n" +
                "    id\n" +
                "    schoolName\n" +
                "    classList{\n" +
                "      id\n" +
                "      className\n" +
                "      schoolId\n" +
                "    }\n" +
                "  }\n" +
                "}";

        String cndStr = "{\n" +
                "\t\"relCndArray\": [{\n" +
                "\t\t\"key\": \"and\",\n" +
                "\t\t\"fieldArray\": [{\n" +
                "\t\t\t\t\"fieldKey\": \"id\",\n" +
                "\t\t\t\t\"operation\": \"eq\",\n" +
                "\t\t\t\t\"fieldValue\": 1\n" +
                "\t\t\t}\n" +
                "\t\t]\n" +
                "\t}]\n" +
                "}";
        ExecutionInput.Builder builder = ExecutionInput.newExecutionInput();
        builder.query(queryStr);
        builder.operationName("query_school");

        Map param = new HashMap();
        param.put("queryCondition", JSON.parse(cndStr));
        builder.variables(param);
        ExecutionResult result = graphQLProvider.graphQL().execute(builder);
        log.info("testQuery4 {}", result);

    }
}
