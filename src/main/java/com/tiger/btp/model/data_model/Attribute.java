//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.11.14 时间 04:26:41 PM CST 
//


package com.tiger.btp.model.data_model;

import javax.xml.bind.annotation.*;


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
 *         &lt;element ref="{http://www.tiger.com/btp/model/data_model}valConfigs" minOccurs="0"/>
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
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="type" use="required" type="{http://www.tiger.com/btp/model/data_model}attributeType" />
 *       &lt;attribute name="unique" type="{http://www.w3.org/2001/XMLSchema}boolean" default="false" />
 *       &lt;attribute name="nullable" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *       &lt;attribute name="length" type="{http://www.w3.org/2001/XMLSchema}int" default="255" />
 *       &lt;attribute name="precision" type="{http://www.w3.org/2001/XMLSchema}int" default="10" />
 *       &lt;attribute name="scale" type="{http://www.w3.org/2001/XMLSchema}int" default="3" />
 *       &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="columnName" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="codeNumber" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="dateFormat" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="defaultValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "valConfigs",
        "relations",
        "viewSchema"
})
@XmlRootElement(name = "attribute")
public class Attribute {

    protected ValConfigs valConfigs;
    protected Relations relations;
    @XmlElement(name = "view_schema")
    protected Attribute.ViewSchema viewSchema;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "type", required = true)
    protected AttributeType type;
    @XmlAttribute(name = "unique")
    protected Boolean unique;
    @XmlAttribute(name = "nullable")
    protected Boolean nullable;
    @XmlAttribute(name = "length")
    protected Integer length;
    @XmlAttribute(name = "precision")
    protected Integer precision;
    @XmlAttribute(name = "scale")
    protected Integer scale;
    @XmlAttribute(name = "label")
    protected String label;
    @XmlAttribute(name = "columnName")
    protected String columnName;
    @XmlAttribute(name = "codeNumber")
    protected String codeNumber;
    @XmlAttribute(name = "dateFormat")
    protected String dateFormat;
    @XmlAttribute(name = "defaultValue")
    protected String defaultValue;

    /**
     * 获取valConfigs属性的值。
     *
     * @return possible object is
     * {@link ValConfigs }
     */
    public ValConfigs getValConfigs() {
        return valConfigs;
    }

    /**
     * 设置valConfigs属性的值。
     *
     * @param value allowed object is
     *              {@link ValConfigs }
     */
    public void setValConfigs(ValConfigs value) {
        this.valConfigs = value;
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
     * {@link Attribute.ViewSchema }
     */
    public Attribute.ViewSchema getViewSchema() {
        return viewSchema;
    }

    /**
     * 设置viewSchema属性的值。
     *
     * @param value allowed object is
     *              {@link Attribute.ViewSchema }
     */
    public void setViewSchema(Attribute.ViewSchema value) {
        this.viewSchema = value;
    }

    /**
     * 获取name属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getName() {
        return name;
    }

    /**
     * 设置name属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setName(String value) {
        this.name = value;
    }

    /**
     * 获取type属性的值。
     *
     * @return possible object is
     * {@link AttributeType }
     */
    public AttributeType getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     *
     * @param value allowed object is
     *              {@link AttributeType }
     */
    public void setType(AttributeType value) {
        this.type = value;
    }

    /**
     * 获取unique属性的值。
     *
     * @return possible object is
     * {@link Boolean }
     */
    public boolean isUnique() {
        if (unique == null) {
            return false;
        } else {
            return unique;
        }
    }

    /**
     * 设置unique属性的值。
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setUnique(Boolean value) {
        this.unique = value;
    }

    /**
     * 获取nullable属性的值。
     *
     * @return possible object is
     * {@link Boolean }
     */
    public boolean isNullable() {
        if (nullable == null) {
            return true;
        } else {
            return nullable;
        }
    }

    /**
     * 设置nullable属性的值。
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setNullable(Boolean value) {
        this.nullable = value;
    }

    /**
     * 获取length属性的值。
     *
     * @return possible object is
     * {@link Integer }
     */
    public int getLength() {
        if (length == null) {
            return 255;
        } else {
            return length;
        }
    }

    /**
     * 设置length属性的值。
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setLength(Integer value) {
        this.length = value;
    }

    /**
     * 获取precision属性的值。
     *
     * @return possible object is
     * {@link Integer }
     */
    public int getPrecision() {
        if (precision == null) {
            return 10;
        } else {
            return precision;
        }
    }

    /**
     * 设置precision属性的值。
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setPrecision(Integer value) {
        this.precision = value;
    }

    /**
     * 获取scale属性的值。
     *
     * @return possible object is
     * {@link Integer }
     */
    public int getScale() {
        if (scale == null) {
            return 3;
        } else {
            return scale;
        }
    }

    /**
     * 设置scale属性的值。
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setScale(Integer value) {
        this.scale = value;
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
     * 获取columnName属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getColumnName() {
        return columnName;
    }

    /**
     * 设置columnName属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setColumnName(String value) {
        this.columnName = value;
    }

    /**
     * 获取codeNumber属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getCodeNumber() {
        return codeNumber;
    }

    /**
     * 设置codeNumber属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setCodeNumber(String value) {
        this.codeNumber = value;
    }

    /**
     * 获取dateFormat属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getDateFormat() {
        return dateFormat;
    }

    /**
     * 设置dateFormat属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDateFormat(String value) {
        this.dateFormat = value;
    }

    /**
     * 获取defaultValue属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getDefaultValue() {
        return defaultValue;
    }

    /**
     * 设置defaultValue属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setDefaultValue(String value) {
        this.defaultValue = value;
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
