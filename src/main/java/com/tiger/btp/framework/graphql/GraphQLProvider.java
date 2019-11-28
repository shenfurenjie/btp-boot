package com.tiger.btp.framework.graphql;

import com.google.common.io.Resources;
import com.tiger.btp.app.DataModelFactory;
import graphql.GraphQL;
import graphql.schema.GraphQLSchema;
import graphql.schema.idl.RuntimeWiring;
import graphql.schema.idl.SchemaGenerator;
import graphql.schema.idl.SchemaParser;
import graphql.schema.idl.TypeDefinitionRegistry;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.io.Charsets;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;

import javax.annotation.PostConstruct;
import java.io.IOException;
import java.net.URL;

import static graphql.schema.idl.RuntimeWiring.newRuntimeWiring;
import static graphql.schema.idl.TypeRuntimeWiring.newTypeWiring;

@Slf4j
//@Component
public class GraphQLProvider {

    private GraphQL graphQL;

    @Autowired
    DataFetcherFactory dataFetcherFactory;

    DataModelFactory dataModelFactory;

    @Bean
    public GraphQL graphQL() {
        return graphQL;
    }

    @PostConstruct
    public void init() throws IOException {
        URL url = Resources.getResource("graphqls/schema.graphql");
        String sdl = Resources.toString(url, Charsets.UTF_8);
        GraphQLSchema graphQLSchema = buildSchema(sdl);
        this.graphQL = GraphQL.newGraphQL(graphQLSchema).build();
    }
    private GraphQLSchema buildSchema(String sdl) {
        TypeDefinitionRegistry typeRegistry = new SchemaParser().parse(sdl);
        RuntimeWiring runtimeWiring = buildWiring();
        SchemaGenerator schemaGenerator = new SchemaGenerator();
        return schemaGenerator.makeExecutableSchema(typeRegistry, runtimeWiring);
    }

    private RuntimeWiring buildWiring() {
        return newRuntimeWiring()
                .type(newTypeWiring("Query").dataFetchers(dataFetcherFactory.getQueryDataFetcher())).build();
    }

}
