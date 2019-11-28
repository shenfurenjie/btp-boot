//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.11.14 时间 04:26:41 PM CST 
//


package com.tiger.btp.model.app;

import javax.xml.bind.annotation.*;
import java.math.BigInteger;


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
 *         &lt;element ref="{http://www.tiger.com/btp/model/app}imports" minOccurs="0"/>
 *         &lt;element ref="{http://www.tiger.com/btp/model/app}configs"/>
 *       &lt;/sequence>
 *       &lt;attribute name="name" use="required" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="label" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="priority" type="{http://www.w3.org/2001/XMLSchema}integer" default="0" />
 *       &lt;attribute name="groupId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="artifactId" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="version" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="snapshotRepositoryURL" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="repositoryURL" type="{http://www.w3.org/2001/XMLSchema}string" />
 *       &lt;attribute name="enable" type="{http://www.w3.org/2001/XMLSchema}boolean" default="true" />
 *     &lt;/restriction>
 *   &lt;/complexContent>
 * &lt;/complexType>
 * </pre>
 */
@XmlAccessorType(XmlAccessType.FIELD)
@XmlType(name = "", propOrder = {
        "imports",
        "configs"
})
@XmlRootElement(name = "app")
public class App {

    protected Imports imports;
    @XmlElement(required = true)
    protected Configs configs;
    @XmlAttribute(name = "name", required = true)
    protected String name;
    @XmlAttribute(name = "label")
    protected String label;
    @XmlAttribute(name = "priority")
    protected BigInteger priority;
    @XmlAttribute(name = "groupId")
    protected String groupId;
    @XmlAttribute(name = "artifactId")
    protected String artifactId;
    @XmlAttribute(name = "version")
    protected String version;
    @XmlAttribute(name = "snapshotRepositoryURL")
    protected String snapshotRepositoryURL;
    @XmlAttribute(name = "repositoryURL")
    protected String repositoryURL;
    @XmlAttribute(name = "enable")
    protected Boolean enable;

    /**
     * 获取imports属性的值。
     *
     * @return possible object is
     * {@link Imports }
     */
    public Imports getImports() {
        return imports;
    }

    /**
     * 设置imports属性的值。
     *
     * @param value allowed object is
     *              {@link Imports }
     */
    public void setImports(Imports value) {
        this.imports = value;
    }

    /**
     * 获取configs属性的值。
     *
     * @return possible object is
     * {@link Configs }
     */
    public Configs getConfigs() {
        return configs;
    }

    /**
     * 设置configs属性的值。
     *
     * @param value allowed object is
     *              {@link Configs }
     */
    public void setConfigs(Configs value) {
        this.configs = value;
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
     * 获取priority属性的值。
     *
     * @return possible object is
     * {@link BigInteger }
     */
    public BigInteger getPriority() {
        if (priority == null) {
            return new BigInteger("0");
        } else {
            return priority;
        }
    }

    /**
     * 设置priority属性的值。
     *
     * @param value allowed object is
     *              {@link BigInteger }
     */
    public void setPriority(BigInteger value) {
        this.priority = value;
    }

    /**
     * 获取groupId属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getGroupId() {
        return groupId;
    }

    /**
     * 设置groupId属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setGroupId(String value) {
        this.groupId = value;
    }

    /**
     * 获取artifactId属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getArtifactId() {
        return artifactId;
    }

    /**
     * 设置artifactId属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setArtifactId(String value) {
        this.artifactId = value;
    }

    /**
     * 获取version属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getVersion() {
        return version;
    }

    /**
     * 设置version属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setVersion(String value) {
        this.version = value;
    }

    /**
     * 获取snapshotRepositoryURL属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getSnapshotRepositoryURL() {
        return snapshotRepositoryURL;
    }

    /**
     * 设置snapshotRepositoryURL属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setSnapshotRepositoryURL(String value) {
        this.snapshotRepositoryURL = value;
    }

    /**
     * 获取repositoryURL属性的值。
     *
     * @return possible object is
     * {@link String }
     */
    public String getRepositoryURL() {
        return repositoryURL;
    }

    /**
     * 设置repositoryURL属性的值。
     *
     * @param value allowed object is
     *              {@link String }
     */
    public void setRepositoryURL(String value) {
        this.repositoryURL = value;
    }

    /**
     * 获取enable属性的值。
     *
     * @return possible object is
     * {@link Boolean }
     */
    public boolean isEnable() {
        if (enable == null) {
            return true;
        } else {
            return enable;
        }
    }

    /**
     * 设置enable属性的值。
     *
     * @param value allowed object is
     *              {@link Boolean }
     */
    public void setEnable(Boolean value) {
        this.enable = value;
    }

}
