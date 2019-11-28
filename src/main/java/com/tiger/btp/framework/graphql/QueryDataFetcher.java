package com.tiger.btp.framework.graphql;

import com.tiger.btp.app.DataModelExt;
import com.tiger.btp.framework.common.utils.ReflectionUtil;
import com.tiger.btp.framework.mapper.BaseMapper;
import com.tiger.btp.model.data_model.Relation;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.Map;

@Slf4j
@Data
public class QueryDataFetcher implements DataFetcher {

    Relation relation;

    DataModelExt model;

    BaseMapper baseMapper;

    Class modelClass;


    @Override
    public Object get(DataFetchingEnvironment environment) {
        DataModelExt modelExt = null;
        if (environment.getSource() == null) {
            modelExt = model;
        }
        Map<String, Object> args = environment.getArguments();
        Class clazz = null;
        try {
            clazz = ReflectionUtil.getClass(modelClass.getGenericSuperclass());
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        }
        return clazz.cast(baseMapper.selectById((Serializable) args.get("id")));
    }
}
