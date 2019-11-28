package com.tiger.btp.framework.graphql.model.condition.template;

import com.tiger.btp.framework.graphql.model.RequestContext;
import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public abstract class BaseTemplate<T> {

    @Getter
    @Setter
    Class<T> clazz;
    @Getter
    @Setter
    String modelId;
    @Getter
    @Setter
    List<String> fetchFields = new ArrayList<>();
    @Getter
    @Setter
    Map<String, Object> vars = new HashMap<>();
    @Getter
    @Setter
    RequestContext webRequest = new RequestContext();

    public BaseTemplate(Class<T> clazz) {
        this.clazz = clazz;
    }

    public abstract String getOperationName();


}
