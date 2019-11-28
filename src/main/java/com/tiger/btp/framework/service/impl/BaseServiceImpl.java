package com.tiger.btp.framework.service.impl;

import com.baomidou.mybatisplus.core.conditions.Wrapper;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.conditions.update.UpdateWrapper;
import com.baomidou.mybatisplus.core.enums.SqlMethod;
import com.baomidou.mybatisplus.core.metadata.IPage;
import com.baomidou.mybatisplus.core.metadata.TableInfo;
import com.baomidou.mybatisplus.core.toolkit.*;
import com.baomidou.mybatisplus.extension.toolkit.SqlHelper;
import com.tiger.btp.framework.common.constants.BaseConstant;
import com.tiger.btp.framework.common.context.AppContextUtil;
import com.tiger.btp.framework.common.utils.ThreadUtil;
import com.tiger.btp.framework.service.BaseService;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.binding.MapperMethod;
import org.apache.ibatis.session.SqlSession;
import org.mybatis.spring.SqlSessionUtils;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.annotation.Transactional;

import java.io.Serializable;
import java.lang.reflect.Field;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.Objects;


/**
 * <p>
 * 基础Service实现 继承于Mybatis-plus
 * </p>
 */

@Slf4j
public class BaseServiceImpl<T, M extends com.baomidou.mybatisplus.core.mapper.BaseMapper<T>> implements BaseService<T>, InitializingBean {


    @Autowired
    protected M baseMapper;

    protected boolean retBool(Integer result) {
        return SqlHelper.retBool(result);
    }

    protected Class<T> currentModelClass() {
        return (Class<T>) ReflectionKit.getSuperClassGenericType(getClass(), 0);
    }

    /**
     * <p>
     * 批量操作 SqlSession
     * </p>
     */
    protected SqlSession sqlSessionBatch() {
        return SqlHelper.sqlSessionBatch(currentModelClass());
    }

    /**
     * 释放sqlSession
     *
     * @param sqlSession session
     */
    protected void closeSqlSession(SqlSession sqlSession) {
        SqlSessionUtils.closeSqlSession(sqlSession, GlobalConfigUtils.currentSessionFactory(currentModelClass()));
    }

    /**
     * 获取SqlStatement
     *
     * @param sqlMethod
     * @return
     */
    protected String sqlStatement(SqlMethod sqlMethod) {
        return SqlHelper.table(currentModelClass()).getSqlStatement(sqlMethod.getMethod());
    }

    @Override
    public void afterPropertiesSet() {
        Field[] fields = getClass().getDeclaredFields();
        try {
            for (Field field : fields) {
                field.setAccessible(true);
                Object v = field.get(this);
                Class<?> cls = field.getType();
                if (v == null && cls.getSimpleName().toLowerCase().contains("service")) {
                    v = AppContextUtil.getBean(cls);
                    field.set(this, v);
                }
                field.setAccessible(false);
            }
        } catch (Exception e) {
            log.error("", e);
            ThreadUtil.sleep(1, 5);
            afterPropertiesSet();
        }
    }

    /**
     * <p>
     * 插入一条记录（选择字段，策略插入）
     * </p>
     *
     * @param entity 实体对象
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean save(T entity) {
        return this.retBool(this.baseMapper.insert(entity));
    }

    /**
     * <p>
     * TableId 注解存在更新记录，否插入一条记录
     * </p>
     *
     * @param entity 实体对象
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdate(T entity) {
        if (entity == null) {
            return false;
        }
        Class<?> cls = entity.getClass();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
        if (null != tableInfo && StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
            Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
            if (StringUtils.checkValNull(idVal)) {
                return save(entity);
            } else {
                /*
                 * 更新成功直接返回，失败执行插入逻辑
                 */
                return Objects.nonNull(getById((Serializable) idVal)) ? updateById(entity) : save(entity);
            }
        } else {
            throw ExceptionUtils.mpe("Error:  Can not execute. Could not find @TableId.");
        }
    }

    /**
     * <p>
     * 批量修改插入
     * </p>
     *
     * @param entityList 实体对象集合
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean saveOrUpdateBatch(Collection<T> entityList) {
        //批量对象插入 不存在直接返回false
        if (CollectionUtils.isEmpty(entityList)) {
            return false;
        }
        Class<?> cls = currentModelClass();
        TableInfo tableInfo = TableInfoHelper.getTableInfo(cls);
        int i = 0;
        try (SqlSession batchSqlSession = sqlSessionBatch()) {
            for (T entity : entityList) {
                if (null != tableInfo && StringUtils.isNotEmpty(tableInfo.getKeyProperty())) {
                    Object idVal = ReflectionKit.getMethodValue(cls, entity, tableInfo.getKeyProperty());
                    if (StringUtils.checkValNull(idVal) || Objects.isNull(getById((Serializable) idVal))) {
                        batchSqlSession.insert(sqlStatement(SqlMethod.INSERT_ONE), entity);
                    } else {
                        MapperMethod.ParamMap<T> param = new MapperMethod.ParamMap<>();
                        param.put(Constants.ENTITY, entity);
                        batchSqlSession.update(sqlStatement(SqlMethod.UPDATE_BY_ID), param);
                    }
                    //不知道以后会不会有人说更新失败了还要执行插入 😂😂😂
                    if (i >= 1 && i % BaseConstant.BATCH_SIZE == 0) {
                        batchSqlSession.flushStatements();
                    }
                    i++;
                } else {
                    throw ExceptionUtils.mpe("Error:  Can not execute. Could not find @TableId.");
                }
            }
            batchSqlSession.flushStatements();
        }
        return true;
    }

    /**
     * <p>
     * 根据 whereEntity 条件，更新记录
     * </p>
     *
     * @param entity        实体对象
     * @param updateWrapper 实体对象封装操作类 {@link UpdateWrapper}
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean update(T entity, Wrapper<T> updateWrapper) {
        return retBool(baseMapper.update(entity, updateWrapper));
    }

    /**
     * <p>
     * 根据 ID 选择修改
     * </p>
     *
     * @param entity 实体对象
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean updateById(T entity) {
        return retBool(baseMapper.updateById(entity));
    }

    /**
     * @param entity
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean logicDel(@Param(Constants.ENTITY) T entity) {
        return this.updateById(entity);
    }


    /**
     * 批量逻辑删除
     *
     * @param entityList 实体对象集合
     * @return
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean logicBatchDel(Collection<T> entityList) {
        return this.saveOrUpdateBatch(entityList);
    }


    /**
     * <p>
     * 根据 entity 条件，删除记录
     * </p>
     *
     * @param queryWrapper 实体包装类 {@link QueryWrapper}
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean remove(Wrapper<T> queryWrapper) {
        return SqlHelper.delBool(baseMapper.delete(queryWrapper));
    }

    /**
     * <p>
     * 根据 ID 删除
     * </p>
     *
     * @param id 主键ID
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeById(Serializable id) {
        return SqlHelper.delBool(baseMapper.deleteById(id));
    }

    /**
     * <p>
     * 根据 columnMap 条件，删除记录
     * </p>
     *
     * @param columnMap 表字段 map 对象
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeByMap(Map<String, Object> columnMap) {
        return SqlHelper.delBool(baseMapper.deleteByMap(columnMap));
    }

    /**
     * <p>
     * 删除（根据ID 批量删除）
     * </p>
     *
     * @param idList 主键ID列表
     */
    @Transactional(rollbackFor = Exception.class)
    @Override
    public boolean removeByIds(Collection<? extends Serializable> idList) {
        return SqlHelper.delBool(baseMapper.deleteBatchIds(idList));
    }

    /**
     * <p>
     * 根据 ID 查询
     * </p>
     *
     * @param id 主键ID
     */
    @Override
    public T getById(Serializable id) {
        return baseMapper.selectById(id);
    }

    /**
     * <p>
     * 查询（根据ID 批量查询）
     * </p>
     *
     * @param idList 主键ID列表
     */
    @Override
    public Collection<T> listByIds(Collection<? extends Serializable> idList) {
        return baseMapper.selectBatchIds(idList);
    }

    /**
     * <p>
     * 查询（根据 columnMap 条件）
     * </p>
     *
     * @param columnMap 表字段 map 对象
     */
    @Override
    public Collection<T> listByMap(Map<String, Object> columnMap) {
        return baseMapper.selectByMap(columnMap);
    }

    /**
     * <p>
     * 根据 Wrapper，查询一条记录
     * </p>
     *
     * @param queryWrapper 实体对象封装操作类 {@link QueryWrapper}
     */
    @Override
    public T getOne(Wrapper<T> queryWrapper) {
        return baseMapper.selectOne(queryWrapper);
    }

    /**
     * <p>
     * 根据 Wrapper 条件，查询总记录数
     * </p>
     *
     * @param queryWrapper 实体对象封装操作类 {@link QueryWrapper}
     */
    @Override
    public int count(Wrapper<T> queryWrapper) {
        return baseMapper.selectCount(queryWrapper);
    }

    /**
     * <p>
     * 查询列表
     * </p>
     *
     * @param queryWrapper 实体对象封装操作类 {@link QueryWrapper}
     */
    @Override
    public List<T> list(Wrapper<T> queryWrapper) {
        return baseMapper.selectList(queryWrapper);
    }

    /**
     * <p>
     * 翻页查询
     * </p>
     *
     * @param page         翻页对象
     * @param queryWrapper 实体对象封装操作类 {@link QueryWrapper}
     */
    @Override
    public IPage<Map<String, Object>> pageMaps(IPage<T> page, Wrapper<T> queryWrapper) {
        return baseMapper.selectMapsPage(page, queryWrapper);
    }

    /**
     * <p>
     * 翻页查询
     * </p>
     *
     * @param page         翻页对象
     * @param queryWrapper 实体对象封装操作类 {@link QueryWrapper}
     */
    @Override
    public IPage<T> pageObject(IPage<T> page, Wrapper<T> queryWrapper) {
        return baseMapper.selectPage(page, queryWrapper);
    }
}
