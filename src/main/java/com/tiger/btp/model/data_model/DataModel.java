//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.11.14 时间 04:26:41 PM CST 
//


package com.tiger.btp.model.data_model;

import javax.xml.bind.annotation.*;
import java.util.ArrayList;
import java.util.List;


/**
 * <p>anonymous complex type的 Java 类。
 * <p>
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;complexContent>
 *     &lt;restriction base="{http://www.w3.org/2001/XMLSchema}anyType">
 *       &lt;sequence>
 *         &lt;element ref="{http://www.tiger.com/btp/model/data_model}attribute" maxOccurs="unbounded"/>
 *         &lt;element ref="{http://www.tiger.com/btp/model/data_model}valGroups" minOccurs="0"/>
 *         &lt;element ref="{http://www.tiger.com/btp/model/data_model}relations" minOccurs="0"/>
 *         &lt;element name="view_schema" minOccurs="0">
 *           &lt;complexType>
 *             &lt;simpleContent>
 *               &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *                 &lt;attribute name="dialect" type="{http://www.tiger.com/btp/model/data_model}DialectType" />
 *               &lt;/extension>
 *             &lt;/simpleContent>
 *           &lt;/complexType>
 *         &lt;/element>
 *       &lt;/sequence>
 *       &lt;attribute name="id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="tableName" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="pkAttribute" type="{http://www.w3.org/2001/XMLSchema}string" default="id" />
 *       &lt;attribute name="pkAuto" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="unionPk" type="{http://www.w3.org/2001/XMLSchema}string" default="" />
 *       &lt;attribute name="view" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="virtual" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="extends" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="display" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="public" type="{http://www.w3.org/2001/XMLSchema}boolean" />
 *       &lt;attribute name="generatorSchema" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "attribute",
        "valGroups",
        "relations",
        "viewSchema"
})
@XmlRootElement(name = "data_model")
public class DataModel {

    @XmlElement(required = true)
    protected List<Attribute> attribute;
    protected ValGroups valGroups;
    protected Relations relations;
    @XmlElement(name = "view_schema")
    protected DataModel.ViewSchema viewSchema;
    @XmlAttribute(name = "id", required = true)
    protected String id;
    @XmlAttribute(name = "tableName", required = true)
    protected String tableName;
    @XmlAttribute(name = "label")
    protected String label;
    @XmlAttribute(name = "pkAttribute")
    protected String pkAttribute;
    @XmlAttribute(name = "pkAuto")
    protected Boolean pkAuto;
    @XmlAttribute(name = "unionPk")
    protected String unionPk;
    @XmlAttribute(name = "view")
    protected Boolean view;
    @XmlAttribute(name = "virtual")
    protected Boolean virtual;
    @XmlAttribute(name = "extends")
    protected String _extends;
    @XmlAttribute(name = "display")
    protected String display;
    @XmlAttribute(name = "public")
    protected Boolean _public;
    @XmlAttribute(name = "generatorSchema")
    protected Boolean generatorSchema;

    /**
     * Gets the value of the attribute property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the attribute property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getAttribute().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Attribute }
     */
    public List<Attribute> getAttribute() {
        if (attribute == null) {
            attribute = new ArrayList<Attribute>();
        }
        return this.attribute;
    }

    /**
     * 获取valGroups属性的值。
     *
     * @return possible object is
     * {@link ValGroups }
     */
    public ValGroups getValGroups() {
        return valGroups;
    }

    /**
     * 设置valGroups属性的值。
     *
     * @param value allowed object is
     *              {@link ValGroups }
     */
    public void setValGroups(ValGroups value) {
        this.valGroups = value;
    }

    /**
     * 获取relations属性的值。
     *
     * @return possible object is
     * {@link Relations }
     */
    public Relations getRelations() {
        return relations;
    }

    /**
     * 设置relations属性的值。
     *
     * @param value allowed object is
     *              {@link Relations }
     */
    public void setRelations(Relations value) {
        this.relations = value;
    }

    /**
     * 获取viewSchema属性的值。
     *
     * @return possible object is
     * {@link DataModel.ViewSchema }
     */
    public DataModel.ViewSchema getViewSchema() {
        return viewSchema;
    }

    /**
     * 设置viewSchema属性的值。
     *
     * @param value allowed object is
     *              {@link DataModel.ViewSchema }
     */
    public void setViewSchema(DataModel.ViewSchema value) {
        this.viewSchema = value;
    }

    /**
     * 获取id属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getId() {
        return id;
    }

    /**
     * 设置id属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setId(String value) {
        this.id = value;
    }

    /**
     * 获取tableName属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getTableName() {
        return tableName;
    }

    /**
     * 设置tableName属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setTableName(String value) {
        this.tableName = value;
    }

    /**
     * 获取label属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getLabel() {
        return label;
    }

    /**
     * 设置label属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setLabel(String value) {
        this.label = value;
    }

    /**
     * 获取pkAttribute属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getPkAttribute() {
        if (pkAttribute == null) {
            return "id";
        } else {
            return pkAttribute;
        }
    }

    /**
     * 设置pkAttribute属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setPkAttribute(String value) {
        this.pkAttribute = value;
    }

    /**
     * 获取pkAuto属性的值。
     *
     * @return possible object is
     * {@link Boolean }
     */
    public boolean isPkAuto() {
        if (pkAuto == null) {
            return false;
        } else {
            return pkAuto;
        }
    }

    /**
     * 设置pkAuto属性的值。
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setPkAuto(Boolean value) {
        this.pkAuto = value;
    }

    /**
     * 获取unionPk属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getUnionPk() {
        if (unionPk == null) {
            return "";
        } else {
            return unionPk;
        }
    }

    /**
     * 设置unionPk属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setUnionPk(String value) {
        this.unionPk = value;
    }

    /**
     * 获取view属性的值。
     *
     * @return possible object is
     * {@link Boolean }
     */
    public boolean isView() {
        if (view == null) {
            return false;
        } else {
            return view;
        }
    }

    /**
     * 设置view属性的值。
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setView(Boolean value) {
        this.view = value;
    }

    /**
     * 获取virtual属性的值。
     *
     * @return possible object is
     * {@link Boolean }
     */
    public boolean isVirtual() {
        if (virtual == null) {
            return false;
        } else {
            return virtual;
        }
    }

    /**
     * 设置virtual属性的值。
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setVirtual(Boolean value) {
        this.virtual = value;
    }

    /**
     * 获取extends属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getExtends() {
        return _extends;
    }

    /**
     * 设置extends属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setExtends(String value) {
        this._extends = value;
    }

    /**
     * 获取display属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getDisplay() {
        return display;
    }

    /**
     * 设置display属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDisplay(String value) {
        this.display = value;
    }

    /**
     * 获取public属性的值。
     *
     * @return possible object is
     * {@link Boolean }
     */
    public Boolean isPublic() {
        return _public;
    }

    /**
     * 设置public属性的值。
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setPublic(Boolean value) {
        this._public = value;
    }

    /**
     * 获取generatorSchema属性的值。
     *
     * @return possible object is
     * {@link Boolean }
     */
    public boolean isGeneratorSchema() {
        if (generatorSchema == null) {
            return true;
        } else {
            return generatorSchema;
        }
    }

    /**
     * 设置generatorSchema属性的值。
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setGeneratorSchema(Boolean value) {
        this.generatorSchema = value;
    }


    /**
     * <p>anonymous complex type的 Java 类。
     * <p>
     * <p>以下模式片段指定包含在此类中的预期内容。
     * <p>
     * <pre>
     * &lt;complexType>
     *   &lt;simpleContent>
     *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
     *       &lt;attribute name="dialect" type="{http://www.tiger.com/btp/model/data_model}DialectType" />
     *     &lt;/extension>
     *   &lt;/simpleContent>
     * &lt;/complexType>
     * </pre>
     */
    @XmlAccessorType(XmlAccessType.FIELD)
    @XmlType(name = "", propOrder = {
            "value"
    })
    public static class ViewSchema {

        @XmlValue
        protected String value;
        @XmlAttribute(name = "dialect")
        protected DialectType dialect;

        /**
         * 获取value属性的值。
         *
         * @return possible object is
         * {@link String }
         */
        public String getValue() {
            return value;
        }

        /**
         * 设置value属性的值。
         *
         * @param value allowed object is
         *              {@link String }
         */
        public void setValue(String value) {
            this.value = value;
        }

        /**
         * 获取dialect属性的值。
         *
         * @return possible object is
         * {@link DialectType }
         */
        public DialectType getDialect() {
            return dialect;
        }

        /**
         * 设置dialect属性的值。
         *
         * @param value allowed object is
         *              {@link DialectType }
         */
        public void setDialect(DialectType value) {
            this.dialect = value;
        }

    }

}
