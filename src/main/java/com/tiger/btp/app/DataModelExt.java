package com.tiger.btp.app;

import com.tiger.btp.model.data_model.Attribute;
import com.tiger.btp.model.data_model.DataModel;
import com.tiger.btp.model.data_model.Relation;
import com.tiger.btp.model.data_model.RelationType;
import lombok.Getter;
import lombok.Setter;
import org.apache.commons.lang3.StringUtils;
import org.springframework.jdbc.support.JdbcUtils;

import java.util.ArrayList;
import java.util.List;

public class DataModelExt extends DataModel {


    @Setter
    @Getter
    DataModelFactory dataModelFactory;


//    @Setter
//    @Getter
//    @JSONField(serialize = false, deserialize = false)
//    DataModel dataModel;

    public String getMapperName() {
        return getClassName() + "BaseMapper";
    }

    public String getClassName() {
        if (this.getId().indexOf("_") > 0) {
            return StringUtils.capitalize(JdbcUtils.convertUnderscoreNameToPropertyName(this.getId()));
        }
        return StringUtils.capitalize(this.getId());
    }

    public String getBaseClassName() {
        return getClassName() + "Base";
    }

    /**
     * 获取dataModel的所有属性
     *
     * @return
     */
    public List<AttributeExt> getColumns() {
        List<AttributeExt> cos = new ArrayList<>();
        for (Attribute attribute : this.getAttribute()) {
            AttributeExt ext = new AttributeExt();
            ext.setAttribute(attribute);
            cos.add(ext);
        }
        return cos;
    }

    public List<RelationExt> getRelation() {
        List<RelationExt> result = new ArrayList<>();
        if (this.getRelations() != null) {
            for (Relation relation : this.getRelations().getRelation()) {
                RelationExt ext = new RelationExt();
                ext.setDataModelFactory(dataModelFactory);
                ext.setRelation(relation);
                result.add(ext);
            }
        }
        return result;
    }

    public List<RelationExt> getArrayRelation() {
        List<RelationExt> result = new ArrayList<>();
        if (this.getRelations() != null) {
            for (Relation relation : this.getRelations().getRelation()) {
                if (relation.getType().equals(RelationType.ARRAY)) {
                    RelationExt ext = new RelationExt();
                    ext.setRelation(relation);
                    result.add(ext);
                }
            }
        }
        return result;
    }


    public List<RelationExt> getObjectRelation() {
        List<RelationExt> result = new ArrayList<>();
        if (this.getRelations() != null) {
            for (Relation relation : this.getRelations().getRelation()) {
                if (relation.getType().equals(RelationType.OBJECT)) {
                    RelationExt ext = new RelationExt();
                    ext.setRelation(relation);
                    result.add(ext);
                }
            }
        }
        return result;
    }

    public Attribute getAttribute(String name) {
        List<Attribute> list = this.getAttribute();
        for (Attribute attribute : list) {
            if (attribute.getName().equalsIgnoreCase(name)) {
                return attribute;
            }
        }
        return null;
    }

    public AttributeExt getAttributeExt(String name) {
        List<AttributeExt> list = this.getColumns();
        for (AttributeExt attribute : list) {
            if (attribute.getName().equalsIgnoreCase(name)) {
                return attribute;
            }
        }
        return null;
    }

    public List<String> getAttributeNames() {
        List<Attribute> list = this.getAttribute();
        List<String> result = new ArrayList<>();

        for (Attribute a : list) {
            result.add(a.getName());
        }
        return result;
    }

}
