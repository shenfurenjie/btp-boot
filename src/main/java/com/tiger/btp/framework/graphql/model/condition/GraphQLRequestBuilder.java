package com.tiger.btp.framework.graphql.model.condition;

import com.alibaba.fastjson.JSON;
import com.google.common.collect.ImmutableMap;
import com.tiger.btp.framework.graphql.GraphQLRequestBody;
import com.tiger.btp.framework.graphql.model.condition.order.OrderBy;
import com.tiger.btp.framework.graphql.model.condition.template.FindByIdTemplate;
import com.tiger.btp.framework.graphql.model.condition.where.Where;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Slf4j
public class GraphQLRequestBuilder {

//    static IdGenerator idGenerator = new IdGenerator(879L);
//
//    protected static String genVarName() {
//        return "v" + idGenerator.nextVal();
//    }

    public static GraphQLRequestBody buildDelete(String modelID, Where where, boolean cascade, Map<String, Object> vars, String... fetchFields) {
        String opts = "delete_" + modelID;
        Map<String, String> params = new HashMap<>();
        params.put("cascade", cascade + "");
        params.put("where", where.toString());
        return buildMutation(opts, ImmutableMap.of(), params, vars, fetchFields);
    }


//    public static GraphQLRequestBody buildUpdate(String modelID, Object _set, Where where, Map<String, Object> vars, String... fetchFields) {
//        String opts = "update_" + modelID;
//        Map<String, String> mutationVars = new HashMap<>();
//        String _setVarName = genVarName();
//        mutationVars.put("$" + _setVarName, modelID + "_mutation_field!");
//        Map<String, String> params = new HashMap<>();
////        params.put("_set", JSONUtils.getJSONStringWithoutQuotation(_set));
//        params.put("_set", "$" + _setVarName);
//        params.put("where", where.toString());
//        if (vars == null) {
//            vars = new HashMap<>();
//        }
//        vars.put(_setVarName, convert2JsonObject(_set));
//        return buildMutation(opts, mutationVars, params, vars, fetchFields);
//    }

//    public static  <T> GraphQLRequestBody buildInsert(String modelID, List<T> objects, Map<String, Object> vars, String... fetchFields) {
//        String opts = "insert_" + modelID;
//        Map<String, String> mutationVars = new HashMap<>();
//        String _setVarName = genVarName();
//        mutationVars.put("$" + _setVarName, "[" + modelID + "_mutation_field!]!");
//        Map<String, String> params = new HashMap<>();
////        params.put("objects", JSONUtils.getJSONStringWithoutQuotation(objects));
//        params.put("objects", "$" + _setVarName);
//        if (vars == null) {
//            vars = new HashMap<>();
//        }
//        vars.put(_setVarName, convert2JsonArray(objects));
//        return buildMutation(opts, mutationVars, params, vars, fetchFields);
//    }

//    public static  <T> GraphQLRequestBody buildSave(String modelID, List<T> objects, Map<String, Object> vars, String... fetchFields) {
//        String opts = "save_" + modelID;
//        Map<String, String> mutationVars = new HashMap<>();
//        String _setVarName = genVarName();
//        mutationVars.put("$" + _setVarName, "[" + modelID + "_mutation_field!]!");
//        Map<String, String> params = new HashMap<>();
////        params.put("objects", JSONUtils.getJSONStringWithoutQuotation(objects));
//        params.put("objects", "$" + _setVarName);
//        if (vars == null) {
//            vars = new HashMap<>();
//        }
//        vars.put(_setVarName, convert2JsonArray(objects));
//        return buildMutation(opts, mutationVars, params, vars, fetchFields);
//    }

    protected static <T> Object convert2JsonArray(List<T> objects) {
        return JSON.parseArray(JSON.toJSONString(objects));
    }


    protected static Object convert2JsonObject(Object object) {
        return JSON.parseObject(JSON.toJSONString(object));
    }

    public static GraphQLRequestBody buildMutation(String mutationOps, Map<String, String> mutationVars, Map<String, String> parameters, Map<String, Object> vars, String... fetchFields) {
        StringBuilder builder = new StringBuilder();
        builder.append("mutation");
        if (mutationVars != null && !mutationVars.isEmpty()) {
            List<String> mvars = new ArrayList<>();
            for (String key : mutationVars.keySet()) {
                String value = mutationVars.get(key);
                mvars.add(key + ":" + value);
            }
            builder.append("(");
            builder.append(StringUtils.join(mvars, ","));
            builder.append(")");
        }
        builder.append(" {");
        builder.append(mutationOps);
        builder.append("(");
        List<String> ps = new ArrayList<>();
        for (String key : parameters.keySet()) {
            ps.add(key + ":" + parameters.get(key));
        }
        builder.append(StringUtils.join(ps, ","));
        builder.append(")");
        buildMutationReturning(builder, fetchFields);
        builder.append("}");
        GraphQLRequestBody body = new GraphQLRequestBody();
        body.setQuery(builder.toString());
        body.setVariables(vars);
        return body;
    }

    protected static StringBuilder buildMutationReturning(StringBuilder builder, String... fetchFields) {
        builder.append(" {");
        builder.append("affected_rows ");
        if (fetchFields != null && fetchFields.length > 0) {
            builder.append("returning {");
            builder.append(StringUtils.join(fetchFields, " "));
            builder.append("  }");
        }
        builder.append(" }");
        return builder;
    }


    /**
     * @param findByIdTemplate
     * @param fetchFields
     * @return
     */
    public static GraphQLRequestBody buildFindByIdQuery(FindByIdTemplate findByIdTemplate, String... fetchFields) {
        String fetchFieldsStr = StringUtils.join(fetchFields, " ");
        StringBuilder builder = new StringBuilder();
        builder.append("query {");
        builder.append("find_").append(findByIdTemplate.getModelId()).append("by_id");
        builder.append("(");
        builder.append("id:\"").append(JSON.toJSON(findByIdTemplate.getId())).append("\"");
        builder.append(")");
        builder.append("{");
        builder.append(fetchFieldsStr);
        builder.append("}");
        builder.append("}");
        GraphQLRequestBody body = new GraphQLRequestBody();
        body.setQuery(builder.toString());
        log.debug(builder.toString());
        body.setVariables(findByIdTemplate.getVars());
        return body;
    }

    /**
     * @param modeID
     * @param id
     * @param vars
     * @param fetchFields
     * @return
     */
    public static GraphQLRequestBody buildFindByIdQuery(String modeID, Object id, Map<String, Object> vars, String... fetchFields) {
        String fetchFieldsStr = StringUtils.join(fetchFields, " ");
        StringBuilder builder = new StringBuilder();
        builder.append("query {");
        builder.append("find_").append(modeID).append("by_id");
        builder.append("(");
        builder.append("id:\"").append(JSON.toJSON(id)).append("\"");
        builder.append(")");
        builder.append("{");
        builder.append(fetchFieldsStr);
        builder.append("}");
        builder.append("}");
        GraphQLRequestBody body = new GraphQLRequestBody();
        body.setQuery(builder.toString());
        log.debug(builder.toString());
        body.setVariables(vars);
        return body;
    }

    public static GraphQLRequestBody buildSimpleQuery(String modeID, Map<String, Object> vars, Where where, OrderBy orderBy, String... fetchFields) {
        String fetchFieldsStr = StringUtils.join(fetchFields, " ");
        GraphQLRequestBody body = commonBuildQuery(modeID + "_simple", vars, where, null, null, orderBy, null, fetchFieldsStr);
        return body;
    }


    public static GraphQLRequestBody buildQuery(String modeID, Map<String, Object> vars, Where where, Integer pageSize, Integer pageNo, OrderBy orderBy, String distinctOn, String... fetchFields) {
        String fetchFieldsStr = StringUtils.join(fetchFields, " ");
        fetchFieldsStr = "page_no page_size total_count objects {" + fetchFieldsStr + "}";
        GraphQLRequestBody body = commonBuildQuery(modeID, vars, where, pageSize, pageNo, orderBy, distinctOn, fetchFieldsStr);
        return body;
    }

    public static GraphQLRequestBody commonBuildQuery(String operation, Map<String, Object> vars, Where where, Integer pageSize, Integer pageNo, OrderBy orderBy, String distinctOn, String fetchFieldsStr) {
        String whereStr = where == null ? null : where.toString();
        String orderByStr = orderBy == null ? null : orderBy.toString();
//        String fetchFieldsStr = StringUtils.join(fetchFields, " ");
        GraphQLRequestBody body = buildQuery(operation, vars, whereStr, pageSize, pageNo, orderByStr, distinctOn, fetchFieldsStr);
        return body;
    }


    public static GraphQLRequestBody buildQuery(String operation, Map<String, Object> vars, String where, Integer pageSize, Integer pageNo, String orderBy, String distinctOn, String fetchFields) {
        StringBuilder builder = new StringBuilder();
        builder.append(" query {");
        builder.append(operation);
        appendWhere(builder, where, pageSize, pageNo, orderBy, distinctOn);
        builder.append(" {");
        builder.append(fetchFields);
        builder.append("}");
        builder.append("}");
        String query = builder.toString();
        GraphQLRequestBody body = new GraphQLRequestBody();
        body.setQuery(query);
        body.setVariables(vars);
        return body;
    }


    protected static StringBuilder appendWhere(StringBuilder builder, String where, Integer pageSize, Integer pageNo, String orderBy, String distinctOn) {
        if (StringUtils.isEmpty(where) && pageNo == null && pageSize == null && StringUtils.isEmpty(orderBy) && StringUtils.isEmpty(distinctOn)) {
            return builder;
        }
        builder.append("(");
        List<String> lists = new ArrayList<>();
        if (StringUtils.isNotEmpty(where)) {
            lists.add("where:" + where);
        }
        if (pageSize != null) {
            lists.add("page_size:" + pageSize);
        }
        if (pageNo != null) {
            lists.add("page_no:" + pageNo);
        }
        if (StringUtils.isNotEmpty(orderBy)) {
            lists.add("order_by:" + orderBy);
        }
        if (StringUtils.isNotEmpty(distinctOn)) {
            lists.add("distinct_on:" + distinctOn);
        }
        builder.append(StringUtils.join(lists, ","));
        builder.append(")");
        return builder;
    }


}
