package com.tiger.btp.framework.graphql;

import com.tiger.btp.framework.graphql.model.condition.template.FindByIdTemplate;

public interface GraphQLService {

    <T> T findById(FindByIdTemplate<T> template);
}
