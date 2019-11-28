package com.tiger.btp.app;

import com.tiger.btp.model.data_model.Relation;
import com.tiger.btp.model.data_model.RelationType;
import lombok.Data;
import org.springframework.jdbc.support.JdbcUtils;

/**
 * dataModel关系扩展类
 */
@Data
public class RelationExt {

    Relation relation;

    DataModelFactory dataModelFactory;

    public String getName() {
        return relation.getName();
    }

    public String getJavaName() {
        return JdbcUtils.convertUnderscoreNameToPropertyName(getName());
    }

    public boolean isArray() {
        return relation.getType().equals(RelationType.ARRAY);
    }

    public String getJavaTypeName() {
        return dataModelFactory.findById(relation.getModelId()).getClassName();
    }

}
