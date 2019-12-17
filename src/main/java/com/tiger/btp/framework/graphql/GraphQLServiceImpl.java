package com.tiger.btp.framework.graphql;

import com.alibaba.fastjson.JSON;
import com.tiger.btp.framework.common.exception.BaseException;
import com.tiger.btp.framework.graphql.model.RequestContext;
import com.tiger.btp.framework.graphql.model.condition.GraphQLRequestBuilder;
import com.tiger.btp.framework.graphql.model.condition.template.BaseTemplate;
import com.tiger.btp.framework.graphql.model.condition.template.FindByIdTemplate;
import graphql.ExecutionResult;
import graphql.GraphQLError;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.concurrent.CompletableFuture;

@Slf4j
@Component
public class GraphQLServiceImpl implements GraphQLService {

    @Autowired
    GraphQLProvider graphQLProvider;

    public <T> T extract(Class<T> clazz, Object result) {
        String resultString = JSON.toJSONString(result);
        T t = JSON.parseObject(resultString, clazz);
        return t;
    }

    protected Map getResult(CompletableFuture result) {
        try {
            Map map = (Map) result.get();
            boolean success = !map.containsKey("errors");
            map.put("success", success);
            if (!success) {
                Object message = ((Map) (((List) map.get("errors")).get(0))).get("message");
                throw new BaseException(message.toString());
            }
            return (Map) map.get("data");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    public <T> List<T> extractArray(Class<T> clazz, Object result) {
        String resultString = JSON.toJSONString(result);
        List<T> list = JSON.parseArray(resultString, clazz);
        return list;
    }

//    public <T> QueryResult<T> query(QueryTemplate<T> t) {
//        GraphQLRequestBody body = GraphQLRequestBuilder.buildQuery(t.getModelId(), t.getVars(), t.getWhere(), t.getPageSize(), t.getPageNo(), t.getOrderBy(), t.getDistinctStr(), this.getFetchField(t));
//        return this.invokeGraphqlPostReturnQueryResult(t.getClazz(), body, t.getWebRequest(), t.getOperationName());
//
//    }

    @Override
    public <T> T findById(FindByIdTemplate<T> template) {
        GraphQLRequestBody body = GraphQLRequestBuilder.buildFindByIdQuery(template, this.getFetchField(template));
        return this.executeQuery(template.getClazz(), body, template.getWebRequest(), template.getOperationName());
    }

//    public <T> MutationResponse<T> insert(SaveTemplate<T> tpl) {
//        GraphQLRequestBody body = GraphQLRequestBuilder.buildInsert(tpl.getModelId(), tpl.getObjects(), tpl.getVars(), this.getFetchField(tpl));
//        return this.invokeGraphqlMutation(tpl.getClazz(), body, tpl.getWebRequest(), tpl.getOperationName());
//    }
//
//    public <T> MutationResponse<T> save(SaveTemplate<T> tpl) {
//        GraphQLRequestBody body = GraphQLRequestBuilder.buildSave(tpl.getModelId(), tpl.getObjects(), tpl.getVars(), this.getFetchField(tpl));
//        return this.invokeGraphqlMutation(tpl.getClazz(), body, tpl.getWebRequest(), tpl.getOperationName());
//    }
//
//    public <T> MutationResponse<T> update(UpdateTemplate<T> tpl) {
//        GraphQLRequestBody body = GraphQLRequestBuilder.buildUpdate(tpl.getModelId(), tpl.getSet(), tpl.getWhere(), tpl.getVars(), this.getFetchField(tpl));
//        return this.invokeGraphqlMutation(tpl.getClazz(), body, tpl.getWebRequest(), tpl.getOperationName());
//    }
//
//    public <T> MutationResponse<T> delete(DeleteTemplate<T> tpl) {
//        GraphQLRequestBody body = GraphQLRequestBuilder.buildDelete(tpl.getModelId(), tpl.getWhere(), tpl.isCascade(), tpl.getVars(), this.getFetchField(tpl));
//        return this.invokeGraphqlMutation(tpl.getClazz(), body, tpl.getWebRequest(), tpl.getOperationName());
//    }


    public Object execute(GraphQLRequestBody body) {
        String query = body.getQuery();
        if (StringUtils.isEmpty(query)) {
            throw new BaseException("no available schema exist");
        }

        ExecutionResult executionResult = graphQLProvider.graphQL().execute(GraphQLExecutionProvider.buildExecutionInput(body));
        List<GraphQLError> errors = executionResult.getErrors();
        if (CollectionUtils.isEmpty(errors)) {
            return GraphQLExecutionProvider.buildExecutionResult(executionResult);
        }
        throw new BaseException("errors");
    }


    protected <T> T executeQuery(Class<T> clazz, GraphQLRequestBody body, RequestContext webRequest, String key) {
        Object gqlResult = execute(body);
        Map realResult = getResult((CompletableFuture) gqlResult);
        return extract(clazz, realResult.get(key));
    }


    protected String[] getFetchField(BaseTemplate baseTemplate) {
        List<String> result = new ArrayList<>();
        List<String> fields = baseTemplate.getFetchFields();
        if (CollectionUtils.isEmpty(fields)) {
            throw new BaseException("no available fetchField exist!");
        }
        return (String[]) fields.toArray();
    }
}
