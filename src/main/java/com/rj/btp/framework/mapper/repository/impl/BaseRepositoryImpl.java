package com.rj.btp.framework.mapper.repository.impl;

import com.rj.btp.framework.mapper.repository.BaseRepository;
import com.rj.btp.framework.model.condition.JpaCondtionUtil;
import com.rj.btp.framework.model.condition.QueryCondition;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.collections.CollectionUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.repository.support.JpaEntityInformation;
import org.springframework.data.jpa.repository.support.JpaEntityInformationSupport;
import org.springframework.data.jpa.repository.support.SimpleJpaRepository;

import javax.persistence.EntityManager;
import java.io.Serializable;
import java.util.List;

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
     * 根据条件删除
     *
     * @param queryCondition
     */
    @Override
    public void deleteByCondition(QueryCondition queryCondition) {
        entityManager.remove(queryCondition);
    }
}
