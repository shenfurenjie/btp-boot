//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.11.14 时间 04:26:41 PM CST 
//


package com.tiger.btp.model.app;

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
 *         &lt;element ref="{http://www.tiger.com/btp/model/app}properties" maxOccurs="unbounded" minOccurs="0"/>
 *       &lt;/sequence>
 *       &lt;attribute name="mavenGroupId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="mavenArtifactId" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="mavenVersion" type="{http://www.w3.org/2001/XMLSchema}string" default="1.0-SNAPSHOT" />
 *       &lt;attribute name="scanPackage" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "properties"
})
@XmlRootElement(name = "import")
public class Import {

    protected List<Properties> properties;
    @XmlAttribute(name = "mavenGroupId", required = true)
    protected String mavenGroupId;
    @XmlAttribute(name = "mavenArtifactId", required = true)
    protected String mavenArtifactId;
    @XmlAttribute(name = "mavenVersion")
    protected String mavenVersion;
    @XmlAttribute(name = "scanPackage", required = true)
    protected String scanPackage;

    /**
     * Gets the value of the properties property.
     * <p>
     * <p>
     * This accessor method returns a reference to the live list,
     * not a snapshot. Therefore any modification you make to the
     * returned list will be present inside the JAXB object.
     * This is why there is not a <CODE>set</CODE> method for the properties property.
     * <p>
     * <p>
     * For example, to add a new item, do as follows:
     * <pre>
     *    getProperties().add(newItem);
     * </pre>
     * <p>
     * <p>
     * <p>
     * Objects of the following type(s) are allowed in the list
     * {@link Properties }
     */
    public List<Properties> getProperties() {
        if (properties == null) {
            properties = new ArrayList<Properties>();
        }
        return this.properties;
    }

    /**
     * 获取mavenGroupId属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getMavenGroupId() {
        return mavenGroupId;
    }

    /**
     * 设置mavenGroupId属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMavenGroupId(String value) {
        this.mavenGroupId = value;
    }

    /**
     * 获取mavenArtifactId属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getMavenArtifactId() {
        return mavenArtifactId;
    }

    /**
     * 设置mavenArtifactId属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMavenArtifactId(String value) {
        this.mavenArtifactId = value;
    }

    /**
     * 获取mavenVersion属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getMavenVersion() {
        if (mavenVersion == null) {
            return "1.0-SNAPSHOT";
        } else {
            return mavenVersion;
        }
    }

    /**
     * 设置mavenVersion属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setMavenVersion(String value) {
        this.mavenVersion = value;
    }

    /**
     * 获取scanPackage属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getScanPackage() {
        return scanPackage;
    }

    /**
     * 设置scanPackage属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setScanPackage(String value) {
        this.scanPackage = value;
    }

}
