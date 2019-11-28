package com.tiger.btp.app;

import com.tiger.btp.framework.common.exception.BaseException;
import com.tiger.btp.framework.common.utils.StringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionUtils;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * DataModel的Dao工厂类
 */
@Slf4j
@Data
//@Component
public class DataModelDaoFactory {

    Object lock = new Object();

    /**
     * dataModelDao的容器：key-modelId：value-DataModelDAO
     */
    Map<String, Object> dataModelDAOMap = new ConcurrentHashMap<>();

    DataModelFactory dataModelFactory;

    /**
     * mybatis的SqlSessionFactory
     */
    SqlSessionFactory sessionFactory;

    public DataModelDaoFactory() {
    }

    public DataModelDaoFactory(DataModelFactory dataModelFactory, SqlSessionFactory sessionFactory) {
        this.dataModelFactory = dataModelFactory;
        this.sessionFactory = sessionFactory;
    }

    /**
     * 清除Dao
     */
    public void clearDAO() {
        this.dataModelDAOMap.clear();
    }

    /**
     * 获取dataModel的数据Dao
     *
     * @param id
     * @return
     */
    public Object getDAO(String id) {
        if (!dataModelDAOMap.containsKey(id)) {
            if (!dataModelFactory.exists(id)) {
                throw new BaseException("model id:" + id + " not exists.");
            }
            synchronized (lock) {
                if (!dataModelDAOMap.containsKey(id)) {
                    DataModelExt dataModelExt = dataModelFactory.findById(id);
                    Object instance = createDataModelDao(dataModelExt);
                    dataModelDAOMap.put(id, instance);
                }
            }
        }
        return dataModelDAOMap.get(id);
    }

    protected Object createDataModelDao(DataModelExt dataModelExt) {
        Class daoClass = StringUtil.toUpperCaseFirstOne(dataModelExt.getId() + "Mapper.class").getClass();
        return SqlSessionUtils.getSqlSession(sessionFactory).getMapper(daoClass);
    }
}
