package com.tiger.btp.framework.graphql.model.condition.template;

import com.tiger.btp.framework.graphql.model.condition.order.OrderBy;
import com.tiger.btp.framework.graphql.model.condition.where.Where;

import java.util.List;

public class QueryTemplate<T> extends BaseTemplate<T> {

    Where where;
    OrderBy orderBy;
    Integer pageSize;
    Integer pageNo;
    List<String> distinct;

    public QueryTemplate(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public String getOperationName() {
        return "query_" + modelId;
    }

}
