package com.tiger.btp.app;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.tiger.btp.framework.common.utils.StringUtil;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.apache.ibatis.session.SqlSessionFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * DataModel的Dao工厂类
 */
@Slf4j
@Data
@Component
public class DataModelDaoFactory {

    @Autowired
    DataModelFactory dataModelFactory;

    /**
     * mybatis的SqlSessionFactory
     */
    @Autowired
    SqlSessionFactory sessionFactory;

    /**
     * 获取dataModel的数据Dao
     *
     * @param modelId
     * @return
     */
    public BaseMapper getDataModelDAO(String modelId) {
        DataModelExt dataModelExt = dataModelFactory.findById(modelId);
        String mapperClass = dataModelExt.getBasePackageName() + ".mapper." + StringUtil.toUpperCaseFirstOne(dataModelExt.getId() + "BaseMapper");
        Object object = null;
        try {
            object = sessionFactory.openSession().getMapper(Class.forName(mapperClass));
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
            log.debug("dataModel " + modelId + " has no available dataModelDao named " + mapperClass + ", please check!");
        }
        return (BaseMapper) object;
    }

}
