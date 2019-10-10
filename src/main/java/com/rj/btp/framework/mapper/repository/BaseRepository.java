package com.rj.btp.framework.mapper.repository;

import com.rj.btp.framework.model.condition.QueryCondition;
import org.springframework.data.domain.Page;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.repository.NoRepositoryBean;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.util.List;

@NoRepositoryBean
public interface BaseRepository<T, ID extends Serializable> extends JpaRepository<T, ID>, JpaSpecificationExecutor<T> {

    /**
     * where查询
     *
     * @param queryCondition
     * @return
     */
    List<T> findByCondition(QueryCondition queryCondition);


    /**
     * 根据Id查询
     *
     * @param id
     * @return
     */
    T findModelById(ID id);

    /**
     * 分页查询
     *
     * @param queryCondition
     * @return
     */
    Page<T> findByPage(QueryCondition queryCondition);

    /**
     * 查询总数
     *
     * @param queryCondition
     * @return
     */
    Long findCount(QueryCondition queryCondition);

    /**
     * 新增或修改
     *
     * @param model
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    T saveModel(T model);

    /**
     * 新增或修改,忽略对象中的null值
     *
     * @param model
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    T saveModelExcludeNulls(T model);

    /**
     * 批量新增或修改
     *
     * @param entities
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    List<T> saveModels(Iterable<T> entities);

    /**
     * 批量新增或修改,忽略对象中的null值
     *
     * @param entities
     * @return
     */
    @Modifying
    @Transactional(rollbackFor = Exception.class)
    List<T> saveModelsExcludeNulls(Iterable<T> entities);


    /**
     * 根据条件删除
     *
     * @param queryCondition
     */
    void deleteByCondition(QueryCondition queryCondition);
}
