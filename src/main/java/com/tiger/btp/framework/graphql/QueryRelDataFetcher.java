package com.tiger.btp.framework.graphql;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tiger.btp.app.DataModelDaoFactory;
import com.tiger.btp.app.DataModelExt;
import com.tiger.btp.framework.common.utils.BeanConvertUtil;
import com.tiger.btp.model.data_model.Relation;
import com.tiger.btp.model.data_model.RelationType;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

/**
 * @Author: TigerRen
 * @Date: 2019/12/6 5:32 PM
 */

@Slf4j
@Data
public class QueryRelDataFetcher implements DataFetcher {

    Relation relation;

    DataModelExt model;

    DataModelDaoFactory dataModelDaoFactory;

    Class modelClass;

    @Override
    public Object get(DataFetchingEnvironment dataFetchingEnvironment) {

        List<Map> result = new ArrayList<>();
        //单个实体操作
        if (!isSingleObject(dataFetchingEnvironment)) {
            Object source = dataFetchingEnvironment.getSource();
            Map sourceMap = BeanConvertUtil.beanToMap(source);
            String modelId = relation.getModelId();
            String attribute = relation.getAttributeName();
            String modelAttribute = relation.getModelAttribute();
            String name = relation.getName();

            //一对一关联
            if (relation.getType().equals(RelationType.OBJECT)) {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq(modelAttribute, sourceMap.get(attribute));
                Object relObject = dataModelDaoFactory.getDataModelDAO(modelId).selectOne(queryWrapper);
                return relObject;
            }

            //一对多关联
            if (relation.getType().equals(RelationType.ARRAY)) {
                QueryWrapper queryWrapper = new QueryWrapper();
                queryWrapper.eq(modelAttribute, sourceMap.get(attribute));
                Object relObjectList = dataModelDaoFactory.getDataModelDAO(modelId).selectList(queryWrapper);
                return relObjectList;
            }


        }
        return null;

    }

    private Boolean isSingleObject(DataFetchingEnvironment environment) {
        return relation == null || environment.getSource() == null;
    }
}
