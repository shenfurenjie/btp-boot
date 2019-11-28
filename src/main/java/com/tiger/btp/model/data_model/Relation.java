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
 *       &lt;attribute name="type" use="required" type="{http://www.tiger.com/btp/model/data_model}relationType" />
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="attribute_name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="model_id" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="model_attribute" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "relation")
public class Relation {

    @XmlAttribute(name = "type", required = true)
    protected RelationType type;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "attribute_name", required = true)
    protected String attributeName;
    @XmlAttribute(name = "model_id", required = true)
    protected String modelId;
    @XmlAttribute(name = "model_attribute", required = true)
    protected String modelAttribute;

    /**
     * 获取type属性的值。
     *
     * @return possible object is
     * {@link RelationType }
     */
    public RelationType getType() {
        return type;
    }

    /**
     * 设置type属性的值。
     *
     * @param value allowed object is
     *              {@link RelationType }
     */
    public void setType(RelationType value) {
        this.type = value;
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
     * 获取attributeName属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getAttributeName() {
        return attributeName;
    }

    /**
     * 设置attributeName属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setAttributeName(String value) {
        this.attributeName = value;
    }

    /**
     * 获取modelId属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getModelId() {
        return modelId;
    }

    /**
     * 设置modelId属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setModelId(String value) {
        this.modelId = value;
    }

    /**
     * 获取modelAttribute属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getModelAttribute() {
        return modelAttribute;
    }

    /**
     * 设置modelAttribute属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setModelAttribute(String value) {
        this.modelAttribute = value;
    }

}
