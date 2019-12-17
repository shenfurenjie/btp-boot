package com.tiger.btp.framework.graphql;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.tiger.btp.app.DataModelDaoFactory;
import com.tiger.btp.app.DataModelExt;
import com.tiger.btp.framework.common.exception.BaseException;
import com.tiger.btp.framework.model.condition.ConditionUtil;
import com.tiger.btp.framework.model.condition.MyBaitsConditionUtil;
import com.tiger.btp.framework.model.condition.PageCnd;
import com.tiger.btp.framework.model.condition.QueryCondition;
import com.tiger.btp.model.data_model.Relation;
import graphql.language.Field;
import graphql.language.Selection;
import graphql.schema.DataFetcher;
import graphql.schema.DataFetchingEnvironment;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.*;

/**
 * 分页获取数据
 *
 * @Author: TigerRen
 * @Date: 2019/12/16 4:36 PM
 */
@Slf4j
@Data
public class QueryDataPagedFetcher implements DataFetcher {

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
        //List<Selection> selectionList = new ArrayList<>();
        Set<String> selectFieldSet = new LinkedHashSet<>();
        if (CollectionUtils.isNotEmpty(selectionParamList)) {
            for (Selection selection : selectionParamList) {
                Field field = (Field) selection;
                if (StringUtils.equals(field.getName(), "totalCount")
                        || StringUtils.equals(field.getName(), "size")
                        || StringUtils.equals(field.getName(), "current")
                        || StringUtils.equals(field.getName(), "pages")
                        || StringUtils.equals(field.getName(), "records")
                        ) {
                    continue;
                }
                selectFieldSet.add(field.getName());

            }
        }
        queryWrapper.select(selectFieldSet.toArray(new String[0]));
        //分页
        if (queryCondition.getPageCnd() == null) {
            throw new BaseException("request param has no available pageCnd!");
        }
        PageCnd pageCnd = queryCondition.getPageCnd();
        IPage queryPage = new Page();
        queryPage.setCurrent(pageCnd.getCurrent());
        queryPage.setSize(pageCnd.getSize());
        IPage result = dataModelDaoFactory.getDataModelDAO(model.getId()).selectPage(queryPage, queryWrapper);
        Map<String, Object> mappedResult = new HashMap<>();
        mappedResult.put("records", result.getRecords());
        mappedResult.put("size", pageCnd.getSize());
        mappedResult.put("current", pageCnd.getCurrent());
        mappedResult.put("totalCount", result.getTotal());
        mappedResult.put("pages", result.getPages());
        return mappedResult;
    }
}
