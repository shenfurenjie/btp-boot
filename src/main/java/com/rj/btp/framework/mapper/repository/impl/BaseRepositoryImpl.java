package com.rj.btp.framework.mapper.repository.impl;

import com.rj.btp.framework.common.utils.BeanConvertUtil;
import com.rj.btp.framework.mapper.repository.BaseRepository;
import com.rj.btp.framework.model.condition.JpaCondtionUtil;
import com.rj.btp.framework.model.condition.QueryCondition;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.BeanWrapper;
import org.springframework.beans.BeanWrapperImpl;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;
import org.springframework.util.Assert;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.*;

@Slf4j
public class BaseRepositoryImpl<T, ID extends Serializable> extends SimpleJpaRepository<T, ID> implements BaseRepository<T, ID> {

    EntityManager entityManager;

    Class<T> domainClass;

    JpaEntityInformation<T, ?> entityInformation;


    public BaseRepositoryImpl(Class<T> domainClass, EntityManager em) {
        super(domainClass, em);
        this.domainClass = domainClass;
        this.entityManager = em;
        this.entityInformation = JpaEntityInformationSupport.getEntityInformation(domainClass, em);
    }


    @Override
    public List<T> findByCondition(QueryCondition queryCondition) {
        Specification<T> specification = (Specification<T>) JpaCondtionUtil.buildWhereClause(queryCondition);
        Sort sort = null;
        if (CollectionUtils.isNotEmpty(queryCondition.getSortCndArray())) {
            sort = JpaCondtionUtil.buildSort(queryCondition);
        }
        if (sort != null) {
            return super.findAll(specification, sort);
        } else {
            return super.findAll(specification);
        }
    }

    /**
     * 根据Id查询
     *
     * @param id
     * @return
     */
    @Override
    public T findModelById(ID id) {
        return super.findById(id).get();
    }

    /**
     * 分页查询
     *
     * @param queryCondition
     * @return
     */
    @Override
    public Page<T> findByPage(QueryCondition queryCondition) {
        Specification<T> specification = (Specification<T>) JpaCondtionUtil.buildWhereClause(queryCondition);
        PageRequest pageRequest = JpaCondtionUtil.buildPageRequest(queryCondition);
        Page<T> result = super.findAll(specification, pageRequest);
        return result;
    }

    /**
     * 查询总数
     *
     * @param queryCondition
     * @return
     */
    @Override
    public Long findCount(QueryCondition queryCondition) {
        Specification<T> specification = (Specification<T>) JpaCondtionUtil.buildWhereClause(queryCondition);
        return super.count(specification);
    }

    /**
     * 新增或修改
     *
     * @param model
     * @return
     */
    @Override
    public T saveModel(T model) {
        return super.saveAndFlush(model);
    }

    /**
     * 新增或修改,忽略对象中的null值
     *
     * @param model
     * @return
     */
    @Override
    public T saveModelExcludeNulls(T model) {
        //查询数据库中的现有数据
        Map map = BeanConvertUtil.beanToMap(model);
        T target = this.findModelById((ID) map.get("id"));
        copyNonNullProperties(model, target);
        return this.save(target);
    }

    /**
     * 入参和已有数据转换
     *
     * @param src
     * @param target
     */
    public void copyNonNullProperties(Object src, Object target) {
        BeanUtils.copyProperties(src, target, getNullPropertyNames(src));
    }

    /**
     * 过滤入参中的null值字段
     *
     * @param source
     * @return
     */
    public String[] getNullPropertyNames(Object source) {
        final BeanWrapper src = new BeanWrapperImpl(source);
        java.beans.PropertyDescriptor[] pds = src.getPropertyDescriptors();

        Set<String> emptyNames = new HashSet<String>();
        for (java.beans.PropertyDescriptor pd : pds) {
            Object srcValue = src.getPropertyValue(pd.getName());
            if (srcValue == null) emptyNames.add(pd.getName());
        }
        String[] result = new String[emptyNames.size()];
        return emptyNames.toArray(result);
    }

    /**
     * 批量新增或修改
     *
     * @param entities
     * @return
     */
    @Override
    public List<T> saveModels(Iterable<T> entities) {
        return super.saveAll(entities);
    }

    /**
     * 批量新增或修改,忽略对象中的null值
     *
     * @param entities
     * @return
     */
    @Override
    public List<T> saveModelsExcludeNulls(Iterable<T> entities) {
        Assert.notNull(entities, "The given Iterable of entities not be null!");
        List<T> result = new ArrayList<T>();
        for (T entity : entities) {
            result.add(saveModelExcludeNulls(entity));
        }
        return result;
    }

    /**
     * 根据条件删除
     *
     * @param queryCondition
     */
    @Override
    public void deleteByCondition(QueryCondition queryCondition) {
        entityManager.remove(queryCondition);
    }
}
