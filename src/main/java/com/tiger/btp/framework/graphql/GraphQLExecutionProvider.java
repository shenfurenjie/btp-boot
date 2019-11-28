package com.tiger.btp.framework.graphql;

import graphql.ExecutionInput;
import graphql.ExecutionResult;
import lombok.extern.slf4j.Slf4j;

import java.util.concurrent.CompletableFuture;

/**
 * 提供graphql入参处理服务
 */
@Slf4j
public class GraphQLExecutionProvider {

    public static ExecutionInput buildExecutionInput(GraphQLRequestBody requestBody) {
        ExecutionInput executionInput = ExecutionInput.newExecutionInput()
                .query(requestBody.getQuery())
                .operationName(requestBody.getOperationName())
                .variables(requestBody.getVariables()).build();
        return executionInput;
    }

    public static Object buildExecutionResult(ExecutionResult executionResult) {
        return executionResult.toSpecification();
    }

    public static Object buildExecutionResult(CompletableFuture<ExecutionResult> completableFuture) {
        return completableFuture.thenApply(ExecutionResult::toSpecification);
    }
}
