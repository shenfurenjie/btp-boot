//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.11.14 时间 04:26:41 PM CST 
//


package com.tiger.btp.model.app;

import javax.xml.bind.annotation.*;


/**
 * <p>anonymous complex type的 Java 类。
 * <p>
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;complexType>
 *   &lt;simpleContent>
 *     &lt;extension base="&lt;http://www.w3.org/2001/XMLSchema>string">
 *       &lt;attribute name="key" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="profile" type="{http://www.w3.org/2001/XMLSchema}string" default="default" />
 *     &lt;/extension>
 *   &lt;/simpleContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "value"
})
@XmlRootElement(name = "config")
public class Config {

    @XmlValue
    protected String value;
    @XmlAttribute(name = "key")
    protected String key;
    @XmlAttribute(name = "profile")
    protected String profile;

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
     * 获取key属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getKey() {
        return key;
    }

    /**
     * 设置key属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setKey(String value) {
        this.key = value;
    }

    /**
     * 获取profile属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getProfile() {
        if (profile == null) {
            return "default";
        } else {
            return profile;
        }
    }

    /**
     * 设置profile属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setProfile(String value) {
        this.profile = value;
    }

}
