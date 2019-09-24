package com.rj.btp.framework.model.condition;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.rj.btp.framework.common.exception.BaseException;
import com.rj.btp.framework.model.enums.AndOrEnum;
import com.rj.btp.framework.model.enums.OperationEnum;
import com.rj.btp.framework.model.enums.OrderByEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;

import java.util.Iterator;
import java.util.List;
import java.util.Map;


/**
 * myBatis请求api工具类
 */
@Slf4j
public class MyBaitsConditionUtil extends ConditionUtil {

    /**
     * 构建接口入参
     *
     * @param updateCndMap
     * @return
     */
    public static UpdateWrapper buildUpdateWrapper(Map<String, Object> updateCndMap) {
        String jsonString = JSON.toJSONString(updateCndMap);
        UpdateCondition updateCondition = JSONObject.parseObject(jsonString, UpdateCondition.class);
        return buildUpdateWrapper(updateCondition);
    }

    /**
     * 构建接口入参
     *
     * @param updateCndJson
     * @return
     */
    public static UpdateWrapper buildUpdateWrapper(String updateCndJson) {
        UpdateCondition updateCondition = JSONObject.parseObject(updateCndJson, UpdateCondition.class);
        UpdateWrapper updateWrapper = new UpdateWrapper();
        Map<String, Object> setCndMap = (Map<String, Object>) updateCondition.getSetCndArray();
        Iterator iter = setCndMap.entrySet().iterator();
        while (iter.hasNext()) {
            Map.Entry entry = (Map.Entry) iter.next();
            String key = (String) entry.getKey();
            Object val = entry.getValue();
            updateWrapper.set(key, val);
        }
        return buildUpdateWrapper(updateCondition);
    }

    public static UpdateWrapper buildUpdateWrapper(UpdateCondition updateCondition) {
        List<RelCnd> relCndList = updateCondition.getRelCndArray();
        UpdateWrapper queryWrapper = new UpdateWrapper();
        if (CollectionUtils.isNotEmpty(relCndList)) {
            for (RelCnd relCnd : relCndList) {
                if (StringUtils.equals(AndOrEnum.and.name(), relCnd.getKey().trim().toLowerCase())) {
                    List<FieldWrapper> fieldWrapperList = relCnd.getFieldArray();
                    if (CollectionUtils.isNotEmpty(fieldWrapperList)) {
                        for (FieldWrapper fieldWrapper : fieldWrapperList) {
                            String function = fieldWrapper.getOperation().trim();
                            buildOpertation(function, null, queryWrapper, fieldWrapper);
                        }
                    }
                }
                if (StringUtils.equals(AndOrEnum.or.name(), relCnd.getKey().trim().toLowerCase())) {
                    List<FieldWrapper> fieldWrapperList = relCnd.getFieldArray();
                    if (CollectionUtils.isNotEmpty(fieldWrapperList)) {
                        for (FieldWrapper fieldWrapper : fieldWrapperList) {
                            String function = fieldWrapper.getOperation().trim();
                            queryWrapper.or();
                            buildOpertation(function, null, queryWrapper, fieldWrapper);
                        }
                    }
                }

            }
        }
        return queryWrapper;
    }


    /**
     * 构建接口入参
     *
     * @param queryCndMap
     * @return
     */
    public static QueryWrapper buildQueryWrapper(Map<String, Object> queryCndMap) {
        String jsonString = JSON.toJSONString(queryCndMap);
        QueryCondition queryCondition = JSONObject.parseObject(jsonString, QueryCondition.class);
        return buildQueryWrapper(queryCondition);
    }


    /**
     * 构建接口入参
     *
     * @param queryCndJson
     * @return
     */
    public static QueryWrapper buildQueryWrapper(String queryCndJson) {
        QueryCondition queryCondition = JSONObject.parseObject(queryCndJson, QueryCondition.class);
        return buildQueryWrapper(queryCondition);
    }


    /**
     * 构建接口入参
     *
     * @param queryCondition
     * @return
     */
    public static QueryWrapper buildQueryWrapper(QueryCondition queryCondition) {
        List<RelCnd> relCndList = queryCondition.getRelCndArray();
        QueryWrapper queryWrapper = new QueryWrapper();
        if (CollectionUtils.isNotEmpty(relCndList)) {
            for (RelCnd relCnd : relCndList) {
                if (StringUtils.equals(AndOrEnum.and.name(), relCnd.getKey().trim().toLowerCase())) {
                    List<FieldWrapper> fieldWrapperList = relCnd.getFieldArray();
                    if (CollectionUtils.isNotEmpty(fieldWrapperList)) {
                        for (FieldWrapper fieldWrapper : fieldWrapperList) {
                            String function = fieldWrapper.getOperation().trim();
                            buildOpertation(function, queryWrapper, null, fieldWrapper);
                        }
                    }
                }
                if (StringUtils.equals(AndOrEnum.or.name(), relCnd.getKey().trim().toLowerCase())) {
                    List<FieldWrapper> fieldWrapperList = relCnd.getFieldArray();
                    if (CollectionUtils.isNotEmpty(fieldWrapperList)) {
                        for (FieldWrapper fieldWrapper : fieldWrapperList) {
                            String function = fieldWrapper.getOperation().trim();
                            queryWrapper.or();
                            buildOpertation(function, queryWrapper, null, fieldWrapper);
                        }
                    }
                }

            }
        }
        //排序
        List<SortCnd> sortCndList = queryCondition.getSortCndArray();
        if (CollectionUtils.isNotEmpty(sortCndList)) {
            for (SortCnd sortCnd : sortCndList) {
                if (sortCnd.order == OrderByEnum.asc) {
                    queryWrapper.orderByAsc(sortCnd.colName);
                }
                if (sortCnd.order == OrderByEnum.desc) {
                    queryWrapper.orderByDesc(sortCnd.colName);
                }
            }
        }
        //分组
        List<GroupByCnd> groupByCndList = queryCondition.getGroupByCndArray();
        if (CollectionUtils.isNotEmpty(groupByCndList)) {
            for (GroupByCnd groupByCnd : groupByCndList) {
                queryWrapper.groupBy(groupByCnd.getColName());

            }
        }
        return queryWrapper;
    }

    public static void buildOpertation(String function, QueryWrapper queryWrapper, UpdateWrapper updateWrapper, FieldWrapper fieldWrapper) {
        if (StringUtils.isEmpty(function)) {
            return;
        }
        switch (OperationEnum.valueOf(function)) {
            case eq:
                if (queryWrapper != null)
                    queryWrapper.eq(fieldWrapper.getFieldKey(), fieldWrapper.getFieldValue());
                if (updateWrapper != null)
                    updateWrapper.eq(fieldWrapper.getFieldKey(), fieldWrapper.getFieldValue());
                break;
            case ne:
                if (queryWrapper != null)
                    queryWrapper.ne(fieldWrapper.getFieldKey(), fieldWrapper.getFieldValue());
                if (updateWrapper != null)
                    updateWrapper.ne(fieldWrapper.getFieldKey(), fieldWrapper.getFieldValue());
                break;

            case gt:
                if (queryWrapper != null)
                    queryWrapper.gt(fieldWrapper.getFieldKey(), fieldWrapper.getFieldValue());
                if (updateWrapper != null)
                    updateWrapper.gt(fieldWrapper.getFieldKey(), fieldWrapper.getFieldValue());
                break;

            case ge:
                if (queryWrapper != null)
                    queryWrapper.ge(fieldWrapper.getFieldKey(), fieldWrapper.getFieldValue());
                if (updateWrapper != null)
                    updateWrapper.ge(fieldWrapper.getFieldKey(), fieldWrapper.getFieldValue());
                break;

            case lt:
                if (queryWrapper != null)
                    queryWrapper.lt(fieldWrapper.getFieldKey(), fieldWrapper.getFieldValue());
                if (updateWrapper != null)
                    updateWrapper.lt(fieldWrapper.getFieldKey(), fieldWrapper.getFieldValue());
                break;

            case le:
                if (queryWrapper != null)
                    queryWrapper.le(fieldWrapper.getFieldKey(), fieldWrapper.getFieldValue());
                if (updateWrapper != null)
                    updateWrapper.le(fieldWrapper.getFieldKey(), fieldWrapper.getFieldValue());
                break;


            case like:
                if (queryWrapper != null)
                    queryWrapper.like(fieldWrapper.getFieldKey(), fieldWrapper.getFieldValue());
                if (updateWrapper != null)
                    updateWrapper.like(fieldWrapper.getFieldKey(), fieldWrapper.getFieldValue());
                break;

            case likeLeft:
                if (queryWrapper != null)
                    queryWrapper.likeLeft(fieldWrapper.getFieldKey(), fieldWrapper.getFieldValue());
                if (updateWrapper != null)
                    updateWrapper.likeLeft(fieldWrapper.getFieldKey(), fieldWrapper.getFieldValue());
                break;

            case likeRight:
                if (queryWrapper != null)
                    queryWrapper.likeRight(fieldWrapper.getFieldKey(), fieldWrapper.getFieldValue());
                if (updateWrapper != null)
                    updateWrapper.likeRight(fieldWrapper.getFieldKey(), fieldWrapper.getFieldValue());
                break;


            case in:
                List inValueList = (List) fieldWrapper.getFieldValue();
                if (CollectionUtils.isEmpty(inValueList)) {
                    throw new BaseException("no available operation value exist");
                }
                if (queryWrapper != null)
                    queryWrapper.in(fieldWrapper.getFieldKey(), inValueList.toArray());
                if (updateWrapper != null)
                    updateWrapper.in(fieldWrapper.getFieldKey(), inValueList.toArray());
                break;

            case notIn:
                if (queryWrapper != null)
                    queryWrapper.notIn(fieldWrapper.getFieldKey(), fieldWrapper.getFieldValue());
                if (updateWrapper != null)
                    updateWrapper.notIn(fieldWrapper.getFieldKey(), fieldWrapper.getFieldValue());
                break;

            case isNull:
                if (queryWrapper != null)
                    queryWrapper.isNull(fieldWrapper.getFieldKey());
                if (updateWrapper != null)
                    updateWrapper.isNull(fieldWrapper.getFieldKey());
                break;

            case isNotNull:
                if (queryWrapper != null)
                    queryWrapper.isNotNull(fieldWrapper.getFieldKey());
                if (updateWrapper != null)
                    updateWrapper.isNotNull(fieldWrapper.getFieldKey());
                break;
            case between:
                List betweenValueList = (List) fieldWrapper.getFieldValue();
                if (CollectionUtils.isEmpty(betweenValueList) || betweenValueList.size() != 2) {
                    throw new BaseException("no available operation value exist");
                }
                if (queryWrapper != null)
                    queryWrapper.between(fieldWrapper.getFieldKey(), betweenValueList.get(0), betweenValueList.get(1));
                if (updateWrapper != null)
                    updateWrapper.between(fieldWrapper.getFieldKey(), betweenValueList.get(0), betweenValueList.get(1));
                break;

            default:
                throw new BaseException("no available operation exist");
        }
    }
}
