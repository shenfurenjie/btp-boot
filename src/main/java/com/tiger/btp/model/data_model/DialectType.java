//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.11.14 时间 04:26:41 PM CST 
//


package com.tiger.btp.model.data_model;

import javax.xml.bind.annotation.XmlEnum;
import javax.xml.bind.annotation.XmlEnumValue;
import javax.xml.bind.annotation.XmlType;


/**
 * <p>DialectType的 Java 类。
 * <p>
 * <p>以下模式片段指定包含在此类中的预期内容。
 * <p>
 * <pre>
 * &lt;simpleType name="DialectType">
 *   &lt;restriction base="{http://www.w3.org/2001/XMLSchema}string">
 *     &lt;enumeration value="SqlServer"/>
 *     &lt;enumeration value="Mysql"/>
 *     &lt;enumeration value="Oracle"/>
 *   &lt;/restriction>
 * &lt;/simpleType>
 * </pre>
 */
@XmlType(name = "DialectType")
@XmlEnum
public enum DialectType {

    @XmlEnumValue("SqlServer")
    SQL_SERVER("SqlServer"),
    @XmlEnumValue("Mysql")
    MYSQL("Mysql"),
    @XmlEnumValue("Oracle")
    ORACLE("Oracle");
    private final String value;

    DialectType(String v) {
        value = v;
    }

    public static DialectType fromValue(String v) {
        for (DialectType c : DialectType.values()) {
            if (c.value.equals(v)) {
                return c;
            }
        }
        throw new IllegalArgumentException(v);
    }

    public String value() {
        return value;
    }

}
