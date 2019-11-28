package com.tiger.btp.framework.graphql;


import graphql.schema.GraphQLObjectType;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

@Slf4j
@Data
public class GraphQLSchemaProvider {

    /**
     * map dataModelId:GraphQLObjectType
     */
    Map<String, GraphQLObjectType> dataModelMap = new ConcurrentHashMap<>();

//    public GraphQLObjectType buildGraphQLModel(DataModelExt dataModelExt){
//        GraphQLObjectType graphQLObjectType = newObject().build();
//        List<AttributeExt> cloumns =  dataModelExt.getColumns();
//
//    }
}
