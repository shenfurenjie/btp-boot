package com.tiger.btp.framework.model.condition;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.tiger.btp.framework.model.enums.AndOrEnum;
import com.tiger.btp.framework.model.enums.OperationEnum;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;

import javax.persistence.criteria.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;


/**
 * Jpa的api工具类
 */
@Slf4j
public class JpaConditionUtil {

    /**
     * 构建接口入参QueryCondition
     *
     * @param queryCndMap
     * @return
     */
    public static QueryCondition buildQueryCondition(Map<String, Object> queryCndMap) {
        String jsonString = JSON.toJSONString(queryCndMap);
        QueryCondition queryCondition = JSONObject.parseObject(jsonString, QueryCondition.class);
        return queryCondition;
    }

    /**
     * 构建接口入参QueryCondition
     *
     * @param queryCndJson
     * @return
     */
    public static QueryCondition buildQueryCondition(String queryCndJson) {
        QueryCondition queryCondition = JSONObject.parseObject(queryCndJson, QueryCondition.class);
        return queryCondition;
    }

    /**
     * 构建接口入参
     *
     * @param updateCndMap
     * @return
     */
    public static UpdateCondition buildUpdateCondition(Map<String, Object> updateCndMap) {
        String jsonString = JSON.toJSONString(updateCndMap);
        UpdateCondition updateCondition = JSONObject.parseObject(jsonString, UpdateCondition.class);
        return updateCondition;
    }

    /**
     * 构建接口入参
     *
     * @param updateCndJson
     * @return
     */
    public static UpdateCondition buildUpdateCondition(String updateCndJson) {
        UpdateCondition updateCondition = JSONObject.parseObject(updateCndJson, UpdateCondition.class);
        return updateCondition;
    }

    /**
     * 构建分页排序条件
     *
     * @param queryCondition
     * @return
     */
    public static PageRequest buildPageRequest(QueryCondition queryCondition) {
        PageCnd pageCnd = queryCondition.getPageCnd();
        if (pageCnd == null) {
            pageCnd = new PageCnd();
            queryCondition.setPageCnd(pageCnd);
        }
        List<SortCnd> sortList = queryCondition.getSortCndArray();
        if (CollectionUtils.isEmpty(sortList)) {
            return PageRequest.of(pageCnd.current, pageCnd.size);
        } else {
            List<Sort.Order> orderList = new ArrayList();
            for (SortCnd sortCnd : sortList) {
                Sort.Direction direction = Sort.Direction.fromString(sortCnd.getOrder().name());
                orderList.add(new Sort.Order(direction, sortCnd.getColName()));
            }
            return PageRequest.of(pageCnd.current, pageCnd.size, Sort.by(orderList));
        }
    }

    /**
     * 构建排序条件
     *
     * @param queryCondition
     * @return
     */
    public static Sort buildSort(QueryCondition queryCondition) {
        List<SortCnd> sortList = queryCondition.getSortCndArray();
        if (CollectionUtils.isEmpty(sortList)) {
            return null;
        }
        List<Sort.Order> orderList = new ArrayList();
        for (SortCnd sortCnd : sortList) {
            Sort.Direction direction = Sort.Direction.fromString(sortCnd.getOrder().name());
            orderList.add(new Sort.Order(direction, sortCnd.getColName()));
        }
        return Sort.by(orderList);
    }

    public static Specification<Object> buildGroupByClause(QueryCondition queryCondition) {
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
                //分组
                if (CollectionUtils.isNotEmpty(queryCondition.getGroupByCndArray())) {
                    List<GroupByCnd> groupByCndList = queryCondition.getGroupByCndArray();
                    List<Path> groupByParamArray = new ArrayList<>();
                    for (GroupByCnd groupByCnd : groupByCndList) {
                        groupByParamArray.add(calPath(root, groupByCnd.getColName()));
                    }
                    criteriaQuery.groupBy(groupByParamArray);

                }
                return criteriaQuery.getRestriction();
            }
        };
        return specification;
    }

    /**
     * 构建where条件
     *
     * @param queryCondition
     * @return
     */
    public static Specification<Object> buildWhereClause(QueryCondition queryCondition) {
        Specification specification = new Specification() {
            @Override
            public Predicate toPredicate(Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {

                List<RelCnd> relCndList = queryCondition.getRelCndArray();
                if (CollectionUtils.isEmpty(relCndList)) {
                    return null;
                }
                Predicate p = null;
                for (RelCnd relCnd : relCndList) {
                    Predicate predicate = buildPredicate(relCnd, root, criteriaQuery, criteriaBuilder);
                    if (StringUtils.equals(AndOrEnum.and.name(), relCnd.getKey().trim().toLowerCase())) {
                        if (p == null) {
                            p = criteriaBuilder.and(predicate);
                        } else {
                            p = criteriaBuilder.and(p, predicate);
                        }

                    }
                    if (StringUtils.equals(AndOrEnum.or.name(), relCnd.getKey().trim().toLowerCase())) {
                        if (p == null) {
                            p = criteriaBuilder.or(predicate);
                        } else {
                            p = criteriaBuilder.or(p, predicate);
                        }
                    }
                }
//                //分组
//                if (CollectionUtils.isNotEmpty(queryCondition.getGroupByCndArray())){
//                    List<GroupByCnd> groupByCndList = queryCondition.getGroupByCndArray();
//                    List<Path> groupByParamArray = new ArrayList<>();
//                    for (GroupByCnd groupByCnd:groupByCndList){
//                        groupByParamArray.add(root.get(groupByCnd.getColName()));
//                    }
//                    criteriaQuery.groupBy(groupByParamArray);
//
//                }
                return p;
            }
        };
        return specification;
    }

    public static Predicate buildPredicate(RelCnd relCnd, Root root, CriteriaQuery criteriaQuery, CriteriaBuilder criteriaBuilder) {
        Predicate predicate = null;
        List<Predicate> andList = new ArrayList<>();
        List<Predicate> orList = new ArrayList<>();
        for (FieldWrapper fieldWrapper : relCnd.getFieldArray()) {
            if (StringUtils.equals(AndOrEnum.or.name(), relCnd.getKey().trim().toLowerCase())) {
                orList.add(buildOperate(fieldWrapper.getOperation(), fieldWrapper, root, criteriaQuery, criteriaBuilder));
            }
            if (StringUtils.equals(AndOrEnum.and.name(), relCnd.getKey().trim().toLowerCase())) {
                andList.add(buildOperate(fieldWrapper.getOperation(), fieldWrapper, root, criteriaQuery, criteriaBuilder));
            }
        }
        if (CollectionUtils.isNotEmpty(andList)) {
            Predicate[] p = new Predicate[andList.size()];
            predicate = criteriaBuilder.and(andList.toArray(p));
        } else if (CollectionUtils.isNotEmpty(orList)) {
            Predicate[] p = new Predicate[orList.size()];
            predicate = criteriaBuilder.or(orList.toArray(p));
        } else {
            throw new RuntimeException("'and' and 'or' may not be exist at the same time!");
        }
        //递归计算predicate
        if (CollectionUtils.isNotEmpty(relCnd.getChildCndArray())) {
            for (RelCnd childRelCnd : relCnd.getChildCndArray()) {
                if (StringUtils.equals(AndOrEnum.and.name(), childRelCnd.getKey().trim().toLowerCase())) {
                    Predicate p = buildPredicate(childRelCnd, root, criteriaQuery, criteriaBuilder);
                    if (predicate == null) {
                        predicate = criteriaBuilder.and(p);
                    } else {
                        predicate = criteriaBuilder.and(predicate, p);
                    }
                }
                if (StringUtils.equals(AndOrEnum.or.name(), childRelCnd.getKey().trim().toLowerCase())) {
                    Predicate p = buildPredicate(childRelCnd, root, criteriaQuery, criteriaBuilder);
                    if (predicate == null) {
                        predicate = criteriaBuilder.or(p);
                    } else {
                        predicate = criteriaBuilder.or(predicate, p);
                    }
                }
            }
        }

        return predicate;
    }

    public static Path calPath(Root<Object> root, String field) {
        Path expression = null;
        if (field.contains(".")) {
            String[] names = StringUtils.split(field, ".");
            expression = root.get(names[0]);
            for (int i = 1; i < names.length; i++) {
                expression = expression.get(names[i]);
            }
        } else {
            expression = root.get(field);
        }
        return expression;
    }

    public static Predicate buildOperate(String function, FieldWrapper fieldWrapper, Root<Object> root, CriteriaQuery<?> criteriaQuery, CriteriaBuilder criteriaBuilder) {
        if (StringUtils.isEmpty(function)) {
            return null;
        }
        Path expression = null;
        if (fieldWrapper.getFieldKey().contains(".")) {
            String[] names = StringUtils.split(fieldWrapper.getFieldKey(), ".");
            expression = root.get(names[0]);
            for (int i = 1; i < names.length; i++) {
                expression = expression.get(names[i]);
            }
        } else {
            expression = root.get(fieldWrapper.getFieldKey());
        }

        Predicate p = null;
        switch (OperationEnum.valueOf(function)) {
            case eq:
                p = criteriaBuilder.equal(expression, fieldWrapper.getFieldValue());
                break;
            case ne:
                p = criteriaBuilder.notEqual(expression, fieldWrapper.getFieldValue());
                break;

            case gt:
                p = criteriaBuilder.gt(expression, (Expression<? extends Number>) fieldWrapper.getFieldValue());
                break;

            case ge:
                p = criteriaBuilder.ge(expression, (Expression<? extends Number>) fieldWrapper.getFieldValue());
                break;

            case lt:
                p = criteriaBuilder.lt(expression, (Expression<? extends Number>) fieldWrapper.getFieldValue());
                break;

            case le:
                p = criteriaBuilder.le(expression, (Expression<? extends Number>) fieldWrapper.getFieldValue());
                break;


            case like:
                p = criteriaBuilder.like(expression, "%" + fieldWrapper.getFieldValue() + "%");
                break;

            case likeLeft:
                p = criteriaBuilder.like(expression, fieldWrapper.getFieldValue() + "%");
                break;

            case likeRight:
                p = criteriaBuilder.like(expression, "%" + fieldWrapper.getFieldValue());
                break;


            case in:
                List<Object> inValueList = (List<Object>) fieldWrapper.getFieldValue();
                if (CollectionUtils.isEmpty(inValueList)) {
                    throw new RuntimeException("no available operation value exist");
                }
                p = expression.in(inValueList);
                break;

            case notIn:
                List<Object> notInValueList = (List<Object>) fieldWrapper.getFieldValue();
                if (CollectionUtils.isEmpty(notInValueList)) {
                    throw new RuntimeException("no available operation value exist");
                }
                p = expression.in(notInValueList);
                break;

            case isNull:
                p = criteriaBuilder.isNotNull(expression);
                break;

            case isNotNull:
                p = criteriaBuilder.isNotNull(expression);
                break;

            case between:
                List<Comparable> betweenValueList = (List<Comparable>) fieldWrapper.getFieldValue();
                if (CollectionUtils.isEmpty(betweenValueList) || betweenValueList.size() != 2) {
                    throw new RuntimeException("no available operation value exist");
                }
                p = criteriaBuilder.between(expression, betweenValueList.get(0), betweenValueList.get(1));
                break;

            default:
                throw new RuntimeException("no available operation exist");
        }
        return p;
    }

    public static void main(String[] args) {
        String json1 = "{\n" +
                "\t\"relCndArray\": [{\n" +
                "\t\t\t\"key\": \"and\",\n" +
                "\t\t\t\"fieldArray\": [{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"eq\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"Tom\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"eq\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"Jack\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"key\": \"or\",\n" +
                "\t\t\t\"fieldArray\": [{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"eq\",\n" +
                "\t\t\t\t\t\"paramValue\": \"Tom\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"eq\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"Jack\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

        String json2 = "{\n" +
                "\t\"relCndArray\": [{\n" +
                "\t\t\t\"key\": \"and\",\n" +
                "\t\t\t\"fieldArray\": [{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"eq\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"tom\"\n" +
                "\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"ne\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"jack\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"gt\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"jack\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"ge\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"jack\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"lt\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"jack\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"le\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"jack\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"key\": \"or\",\n" +
                "\t\t\t\"fieldArray\": [{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"like\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"tom\"\n" +
                "\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"likeLeft\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"jack\"\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"likeRight\",\n" +
                "\t\t\t\t\t\"fieldValue\": \"jack\"\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"key\": \"or\",\n" +
                "\t\t\t\"fieldArray\": [{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"in\",\n" +
                "\t\t\t\t\t\"fieldValue\": [\n" +
                "\t\t\t\t\t\t\"tom\", \"jcack\"\n" +
                "\t\t\t\t\t]\n" +
                "\n" +
                "\t\t\t\t},\n" +
                "\t\t\t\t{\n" +
                "\t\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\t\"operation\": \"notIn\",\n" +
                "\t\t\t\t\t\"fieldValue\": [\n" +
                "\t\t\t\t\t\t\"tom\", \"jcack\"\n" +
                "\t\t\t\t\t]\n" +
                "\n" +
                "\t\t\t\t}\n" +
                "\t\t\t]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"key\": \"and\",\n" +
                "\t\t\t\"fieldArray\": [{\n" +
                "\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\"operation\": \"isNull\"\n" +
                "\n" +
                "\t\t\t}]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"key\": \"or\",\n" +
                "\t\t\t\"fieldArray\": [{\n" +
                "\t\t\t\t\"fieldKey\": \"id\",\n" +
                "\t\t\t\t\"operation\": \"between\",\n" +
                "\t\t\t\t\"fieldValue\": [\n" +
                "\t\t\t\t\t1, 2\n" +
                "\t\t\t\t]\n" +
                "\n" +
                "\t\t\t}]\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"key\": \"and\",\n" +
                "\t\t\t\"fieldArray\": [{\n" +
                "\t\t\t\t\"fieldKey\": \"name\",\n" +
                "\t\t\t\t\"operation\": \"isNull\"\n" +
                "\n" +
                "\t\t\t}]\n" +
                "\t\t}\n" +
                "\t],\n" +
                "\t\"sortCndArray\": [{\n" +
                "\t\t\t\"colName\": \"name\",\n" +
                "\t\t\t\"order\": \"asc\"\n" +
                "\t\t},\n" +
                "\t\t{\n" +
                "\t\t\t\"colName\": \"id\",\n" +
                "\t\t\t\"order\": \"desc\"\n" +
                "\t\t}\n" +
                "\t]\n" +
                "}";

        QueryCondition queryCondition = JSONObject.parseObject(json1, QueryCondition.class);
        Specification specification = buildWhereClause(queryCondition);
        log.info("queryCondition is {}", queryCondition);
    }
}
