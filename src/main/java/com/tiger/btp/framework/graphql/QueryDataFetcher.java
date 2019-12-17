package com.tiger.btp.framework.graphql;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.tiger.btp.app.DataModelDaoFactory;
import com.tiger.btp.app.DataModelExt;
import com.tiger.btp.framework.model.condition.ConditionUtil;
import com.tiger.btp.framework.model.condition.MyBaitsConditionUtil;
import com.tiger.btp.framework.model.condition.QueryCondition;
import com.tiger.btp.model.data_model.Relation;
import graphql.language.Field;
import graphql.language.FragmentSpread;
import graphql.language.Selection;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;

import java.util.LinkedHashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

@Slf4j
@Data
public class QueryDataFetcher implements DataFetcher {

    Relation relation;

    DataModelExt model;

    DataModelDaoFactory dataModelDaoFactory;

    Class modelClass;


    @Override
    public Object get(DataFetchingEnvironment dataFetchingEnvironment) {

        //请求参数
        Map<String, Object> argsMap = dataFetchingEnvironment.getArguments();
        QueryCondition queryCondition = ConditionUtil.buildQueryCondition((Map<String, Object>) argsMap.get("queryCondition"));
        QueryWrapper queryWrapper = MyBaitsConditionUtil.buildQueryWrapper(queryCondition);

        //fetchColumns
        List<Selection> selectionParamList = dataFetchingEnvironment.getField().getSelectionSet().getSelections();
        Set<String> selectFieldSet = new LinkedHashSet<>();
        if (CollectionUtils.isNotEmpty(selectionParamList)) {
            for (Selection selection : selectionParamList) {
                //TODO 待挖掘使用方式
                if (selection instanceof FragmentSpread) {
                    FragmentSpread fragmentSpread = (FragmentSpread) selection;
                    String name = fragmentSpread.getName();
                    //selectionList.addAll(dataFetchingEnvironment.getFragmentsByName().get(name).getSelectionSet().getSelections());
                } else {
                    Field field = (Field) selection;
                    if (field.getSelectionSet() != null) {
                        continue;
                    }
                    selectFieldSet.add(field.getName());
                    //selectionList.add(selection);
                }
            }
        }
        queryWrapper.select(selectFieldSet.toArray(new String[0]));
        Object result = dataModelDaoFactory.getDataModelDAO(model.getId()).selectList(queryWrapper);
        return result;
    }


    private Boolean isSingleObject(DataFetchingEnvironment environment) {
        return relation == null || environment.getSource() == null;
    }
}
