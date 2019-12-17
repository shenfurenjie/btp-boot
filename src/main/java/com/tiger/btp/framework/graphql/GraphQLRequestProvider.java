package com.tiger.btp.framework.graphql;

import com.alibaba.fastjson.JSON;
import com.tiger.btp.framework.model.condition.QueryCondition;
import com.tiger.btp.framework.model.condition.RelCnd;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.List;

/**
 * @Author: TigerRen
 * @Date: 2019/12/13 8:51 AM
 */
public class GraphQLRequestProvider {


    public static GraphQLRequestBody buildQuery(String operation, QueryCondition queryCondition, String... fetchFields) {
        StringBuilder builder = new StringBuilder();
        builder.append(" query {");
        builder.append(operation);
        appendQueryCnd(builder, queryCondition);
        builder.append(" {");
        String fetchFieldsStr = StringUtils.join(fetchFields, " ");
        builder.append(fetchFieldsStr);
        builder.append("}");
        builder.append("}");
        String query = builder.toString();
        GraphQLRequestBody body = new GraphQLRequestBody();
        body.setQuery(query);
        //body.setVariables(vars);
        return body;
    }

    protected static StringBuilder appendQueryCnd(StringBuilder builder, QueryCondition queryCondition) {
        List<RelCnd> relCndList = queryCondition.getRelCndArray();
        if (CollectionUtils.isEmpty(relCndList)) {
            return builder;
        }
        builder.append("(");
        builder.append("queryCondition: " + JSON.toJSONString(queryCondition));
        builder.append(")");
        return builder;
    }


}
