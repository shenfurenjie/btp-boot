package com.tiger.btp.framework.graphql;


import com.google.common.collect.ImmutableMap;
import com.tiger.btp.app.DataModelExt;
import com.tiger.btp.app.DataModelFactory;
import com.tiger.btp.framework.common.exception.BaseException;
import com.tiger.btp.framework.common.utils.FtlUtils;
import freemarker.template.TemplateException;
import graphql.schema.DataFetcher;
import graphql.schema.GraphQLObjectType;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.*;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.IOUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;

@Slf4j
@Data
@Component
public class GraphQLSchemaProvider {

    @Autowired
    DataModelFactory dataModelFactory;

    @Autowired
    DataFetcherFactory dataFetcherFactory;

    /**
     * map dataModelId:GraphQLObjectType
     */
    Map<String, GraphQLObjectType> graphQLObjectMap = new ConcurrentHashMap<>();


    public GraphQLObjectType getGraphQLObject(String dataModelId) {
        if (isExist(dataModelId)) {
            return graphQLObjectMap.get(dataModelId);
        }
        throw new BaseException("no available GraphQLObject【" + dataModelId + "】 exist");
    }

    public boolean isExist(String dataModelId) {
        return graphQLObjectMap.containsKey(dataModelId);
    }

    /**
     * 构建graphQLSchema
     *
     * @param sdl
     * @return
     */
    public GraphQLSchema buildGraphQLSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = buildTypeDefinitionRegistry(sdl);
        RuntimeWiring runtimeWiring = buildRuntimeWiring();
        GraphQLSchema graphQLSchema = new SchemaGenerator().makeExecutableSchema(typeRegistry, runtimeWiring);
        return graphQLSchema;
    }


    /**
     * 获取SDL
     *
     * @return
     */
    public String getSDL(String sdlPath) {
        InputStream io = GraphQLSchemaProvider.class.getClassLoader().getResourceAsStream(sdlPath);
        String sdl = "";
        try {
            sdl = FtlUtils.render2(IOUtils.toString(io, "utf-8"), ImmutableMap.of("model", dataModelFactory.getDataModelExtMap()));
            log.debug("graphQL sdl is {}", sdl);
        } catch (IOException e) {
            e.printStackTrace();
        } catch (TemplateException e) {
            e.printStackTrace();
        }
        return sdl;
    }

    /**
     * 构建返回类型
     *
     * @param sdl
     * @return
     */
    public TypeDefinitionRegistry buildTypeDefinitionRegistry(String sdl) {
        SchemaParser schemaParser = new SchemaParser();
        TypeDefinitionRegistry typeDefinitionRegistry = new TypeDefinitionRegistry();
        typeDefinitionRegistry.merge(schemaParser.parse(sdl));
        return typeDefinitionRegistry;
    }

    /**
     * 构建运行时关联
     *
     * @return
     */
    public RuntimeWiring buildRuntimeWiring() {
        RuntimeWiring.Builder wiringBuilder = newRuntimeWiring()
                .type(TypeRuntimeWiring.newTypeWiring("Query").dataFetchers(dataFetcherFactory.getQueryDataFetcher()));
        //.type(TypeRuntimeWiring.newTypeWiring("MutationType").dataFetchers(dataFetcherFactory.getMutationFetcher()));
        //.type(TypeRuntimeWiring.newTypeWiring("Code").dataFetchers(dataFetcherFactory.getCodeFetcherProxy()));

        //处理dataModel的关联对象
        for (DataModelExt ext : dataModelFactory.getDataModelExtMap().values()) {
            Map<String, DataFetcher> relDataAttributeDataFetcher = dataFetcherFactory.getRelDataModelFetcher(ext);
            Map<String, DataFetcher> resultFetcher = dataFetcherFactory.getQueryResultFetcher(ext);
            if (relDataAttributeDataFetcher != null && relDataAttributeDataFetcher.size() > 0) {
                wiringBuilder.type(TypeRuntimeWiring.newTypeWiring(ext.getClassName()).dataFetchers(relDataAttributeDataFetcher));
            }
            //wiringBuilder.type(TypeRuntimeWiring.newTypeWiring(ext.getQueryResultName()).dataFetchers(resultFetcher));
        }
        //wiringBuilder.directive("dateFormat", new DateFormatting());
        RuntimeWiring runtimeWiring = wiringBuilder.build();
        return runtimeWiring;
    }

}
