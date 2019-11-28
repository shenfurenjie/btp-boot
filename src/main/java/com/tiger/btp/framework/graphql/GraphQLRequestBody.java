package com.tiger.btp.framework.graphql;

import java.util.Map;

public class GraphQLRequestBody {

    private String query;
    private String operationName;
    private Map<String, Object> variables;

    public GraphQLRequestBody() {
    }

    public String getQuery() {
        return this.query;
    }

    public void setQuery(String query) {
        this.query = query;
    }

    public String getOperationName() {
        return this.operationName;
    }

    public void setOperationName(String operationName) {
        this.operationName = operationName;
    }

    public Map<String, Object> getVariables() {
        return this.variables;
    }

    public void setVariables(Map<String, Object> variables) {
        this.variables = variables;
    }
}
