package com.tiger.btp.app;

import com.tiger.btp.framework.model.type.ColumnType;
import com.tiger.btp.framework.model.type.utils.ColumnTypeUtil;
import com.tiger.btp.model.data_model.Attribute;
import lombok.Data;
import org.springframework.jdbc.support.JdbcUtils;

/**
 * dataModel属性扩展类
 */
@Data
public class AttributeExt {

    Attribute attribute;

    /**
     * 数据库列名称
     *
     * @return
     */
    public String getName() {
        return attribute.getName();
    }

    public String getColumnName() {
        return attribute.getColumnName();
    }

    /**
     * 转换成POJO的字段，驼峰模式
     *
     * @return
     */
    public String getJavaName() {
        String name = getName();
        if (name.indexOf("_") > 0) {
            return JdbcUtils.convertUnderscoreNameToPropertyName(getName());
        } else {
            return name;
        }

    }

    public String getJavaType() {
        return ColumnTypeUtil.getJavaClass(ColumnType.valueOf(attribute.getType().value())).getName();
    }

    public String getHbmType() {
        return ColumnTypeUtil.getHbmType(ColumnType.valueOf(attribute.getType().value()));
    }

//    public String getType() {
//        return
//        if (attribute.getType().value().equalsIgnoreCase(ColumnType.CODE.name())) {
//            return "Code";
//        }
//        if (attribute.getType().value().equalsIgnoreCase(ColumnType.TIMESTAMP.name())) {
//            String result = ColumnTypeUtil.getGraphQLType(attribute.getType().value()).getName() + " @dateFormat";
//            if (StringUtils.isNotEmpty(attribute.getDateFormat())) {
//                result = result + "(format:\"" + attribute.getDateFormat().trim() + "\")";
//            }
//            return result;
//        }
//        return ColumnTypeUtil.getGraphQLType(attribute.getType().value()).getName();
//    }

}
