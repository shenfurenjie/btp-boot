package com.rj.btp.framework.id;

import com.rj.btp.framework.common.exception.BaseException;
import org.hibernate.HibernateException;
import org.hibernate.MappingException;
import org.hibernate.engine.spi.SharedSessionContractImplementor;
import org.hibernate.id.Configurable;
import org.hibernate.id.IdentifierGenerator;
import org.hibernate.service.ServiceRegistry;
import org.hibernate.type.Type;

import java.io.Serializable;
import java.util.Properties;

/**
 * 自定义 Snowflake 的ID生成策略
 */
public class SnowflakeIdGenerator implements Configurable, IdentifierGenerator {


    @Override
    public Serializable generate(SharedSessionContractImplementor sharedSessionContractImplementor, Object o) throws HibernateException {
        Object id = SnowflakeIdHelper.getId();
        if (id != null) {
            return (Serializable) id;
        } else {
            throw new BaseException("generator ID has exception");
        }
    }

    @Override
    public void configure(Type type, Properties params, ServiceRegistry serviceRegistry) throws MappingException {

    }
}
