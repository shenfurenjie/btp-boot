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
 *       &lt;attribute name="valRule" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="valRuleValue" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="message" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="valGroup" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="integer" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="fraction" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="min" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="max" type="{http://www.w3.org/2001/XMLSchema}int" />
 *       &lt;attribute name="regexp" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "")
@XmlRootElement(name = "valConfig")
public class ValConfig {

    @XmlAttribute(name = "valRule")
    protected String valRule;
    @XmlAttribute(name = "valRuleValue")
    protected String valRuleValue;
    @XmlAttribute(name = "message")
    protected String message;
    @XmlAttribute(name = "valGroup")
    protected String valGroup;
    @XmlAttribute(name = "integer")
    protected Integer integer;
    @XmlAttribute(name = "fraction")
    protected Integer fraction;
    @XmlAttribute(name = "min")
    protected Integer min;
    @XmlAttribute(name = "max")
    protected Integer max;
    @XmlAttribute(name = "regexp")
    protected String regexp;
    @XmlAttribute(name = "label")
    protected String label;

    /**
     * 获取valRule属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getValRule() {
        return valRule;
    }

    /**
     * 设置valRule属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setValRule(String value) {
        this.valRule = value;
    }

    /**
     * 获取valRuleValue属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getValRuleValue() {
        return valRuleValue;
    }

    /**
     * 设置valRuleValue属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setValRuleValue(String value) {
        this.valRuleValue = value;
    }

    /**
     * 获取message属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getMessage() {
        return message;
    }

    /**
     * 设置message属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMessage(String value) {
        this.message = value;
    }

    /**
     * 获取valGroup属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getValGroup() {
        return valGroup;
    }

    /**
     * 设置valGroup属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setValGroup(String value) {
        this.valGroup = value;
    }

    /**
     * 获取integer属性的值。
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getInteger() {
        return integer;
    }

    /**
     * 设置integer属性的值。
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setInteger(Integer value) {
        this.integer = value;
    }

    /**
     * 获取fraction属性的值。
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getFraction() {
        return fraction;
    }

    /**
     * 设置fraction属性的值。
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setFraction(Integer value) {
        this.fraction = value;
    }

    /**
     * 获取min属性的值。
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getMin() {
        return min;
    }

    /**
     * 设置min属性的值。
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setMin(Integer value) {
        this.min = value;
    }

    /**
     * 获取max属性的值。
     *
     * @return possible object is
     * {@link Integer }
     */
    public Integer getMax() {
        return max;
    }

    /**
     * 设置max属性的值。
     *
     * @param value allowed object is
     *              {@link Integer }
     */
    public void setMax(Integer value) {
        this.max = value;
    }

    /**
     * 获取regexp属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getRegexp() {
        return regexp;
    }

    /**
     * 设置regexp属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRegexp(String value) {
        this.regexp = value;
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

}
