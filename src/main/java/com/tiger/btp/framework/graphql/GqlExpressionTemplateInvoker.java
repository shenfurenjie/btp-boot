package com.tiger.btp.framework.graphql;

import com.tiger.btp.app.DataModelFactory;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
public class GqlExpressionTemplateInvoker {

    DataModelFactory dataModelFactory;

//    public final static Map<ModelType, HttpRestTemplateRender> invokerMap = new ConcurrentHashMap<>();
//
//
//    static {
//        invokerMap.put(ModelType.SAVE, new CommonHttpRestTemplateRender(ModelType.SAVE));
//        invokerMap.put(ModelType.DELETE, new CommonHttpRestTemplateRender(ModelType.DELETE));
//        invokerMap.put(ModelType.DELETE_BY_ID, new CommonHttpRestTemplateRender(ModelType.DELETE_BY_ID));
//        invokerMap.put(ModelType.INSERT, new CommonHttpRestTemplateRender(ModelType.INSERT));
//        invokerMap.put(ModelType.UPDATE, new CommonHttpRestTemplateRender(ModelType.UPDATE));
//        invokerMap.put(ModelType.QUERY_LIST, new CommonHttpRestTemplateRender(ModelType.QUERY_LIST));
//        invokerMap.put(ModelType.QUERY_PAGE, new CommonHttpRestTemplateRender(ModelType.QUERY_PAGE));
//        invokerMap.put(ModelType.QUERY_BY_ID, new CommonHttpRestTemplateRender(ModelType.QUERY_BY_ID));
//        invokerMap.put(ModelType.QUERY_TREE_LIST, new CommonHttpRestTemplateRender(ModelType.QUERY_TREE_LIST));
//    }
//
//    /**
//     * 获取graphql的表达式
//     * @param rest
//     * @param webRequest
//     * @return
//     */
//    public String getGraphQLExpression(HttpRest rest, RequestContext webRequest) {
//        ModelType modelType = rest.getMode();
//        if (modelType == null) {
//            return rest.getGqlExpression().getValue();
//        }
//        DataModelExt modelExt = dataModelFactory.findById(rest.getModelId());
//        String expression = invokerMap.get(modelType).getExpression(rest, modelExt, webRequest);
//        return expression;
//    }
}
