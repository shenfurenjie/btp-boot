package com.tiger.btp.framework.graphql.model;

import lombok.Getter;
import lombok.Setter;

import java.util.HashMap;
import java.util.Map;

/**
 * graphQL请求上下文信息
 */
public class RequestContext {

    @Getter
    @Setter
    String appServiceName;

    @Setter
    @Getter
    String requestId;

    @Getter
    @Setter
    Map<String, Object> parameters = new HashMap<>();

    @Getter
    @Setter
    Map<String, Object> context = new HashMap<>();

    @Getter
    @Setter
    Object result;
}
