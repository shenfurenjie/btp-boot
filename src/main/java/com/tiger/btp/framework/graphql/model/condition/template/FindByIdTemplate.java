package com.tiger.btp.framework.graphql.model.condition.template;

import lombok.Getter;
import lombok.Setter;

import java.io.Serializable;


public class FindByIdTemplate<T> extends BaseTemplate<T> {

    @Getter
    @Setter
    Serializable id;

    public FindByIdTemplate(Class<T> clazz) {
        super(clazz);
    }

    @Override
    public String getOperationName() {
        return "query_" + modelId + "_by_id";
    }
}
