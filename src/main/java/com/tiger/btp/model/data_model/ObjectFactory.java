//
// 此文件是由 JavaTM Architecture for XML Binding (JAXB) 引用实现 v2.2.8-b130911.1802 生成的
// 请访问 <a href="http://java.sun.com/xml/jaxb">http://java.sun.com/xml/jaxb</a> 
// 在重新编译源模式时, 对此文件的所有修改都将丢失。
// 生成时间: 2019.11.14 时间 04:26:41 PM CST 
//


package com.tiger.btp.model.data_model;

import javax.xml.bind.annotation.XmlRegistry;


/**
 * This object contains factory methods for each
 * Java content interface and Java element interface
 * generated in the com.tiger.btp.model.data_model package.
 * <p>An ObjectFactory allows you to programatically
 * construct new instances of the Java representation
 * for XML content. The Java representation of XML
 * content can consist of schema derived interfaces
 * and classes representing the binding of schema
 * type definitions, element declarations and model
 * groups.  Factory methods for each of these are
 * provided in this class.
 */
@XmlRegistry
public class ObjectFactory {


    /**
     * Create a new ObjectFactory that can be used to create new instances of schema derived classes for package: com.tiger.btp.model.data_model
     */
    public ObjectFactory() {
    }

    /**
     * Create an instance of {@link Attribute }
     */
    public Attribute createAttribute() {
        return new Attribute();
    }

    /**
     * Create an instance of {@link DataModel }
     */
    public DataModel createDataModel() {
        return new DataModel();
    }

    /**
     * Create an instance of {@link ValConfigs }
     */
    public ValConfigs createValConfigs() {
        return new ValConfigs();
    }

    /**
     * Create an instance of {@link ValConfig }
     */
    public ValConfig createValConfig() {
        return new ValConfig();
    }

    /**
     * Create an instance of {@link ValGroups }
     */
    public ValGroups createValGroups() {
        return new ValGroups();
    }

    /**
     * Create an instance of {@link ValGroup }
     */
    public ValGroup createValGroup() {
        return new ValGroup();
    }

    /**
     * Create an instance of {@link DataModels }
     */
    public DataModels createDataModels() {
        return new DataModels();
    }

    /**
     * Create an instance of {@link Relations }
     */
    public Relations createRelations() {
        return new Relations();
    }

    /**
     * Create an instance of {@link Relation }
     */
    public Relation createRelation() {
        return new Relation();
    }

    /**
     * Create an instance of {@link Attribute.ViewSchema }
     */
    public Attribute.ViewSchema createAttributeViewSchema() {
        return new Attribute.ViewSchema();
    }

    /**
     * Create an instance of {@link DataModel.ViewSchema }
     */
    public DataModel.ViewSchema createDataModelViewSchema() {
        return new DataModel.ViewSchema();
    }

}
