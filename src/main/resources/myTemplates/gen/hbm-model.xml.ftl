<?xml version="1.0" encoding="utf-8"?>
<!DOCTYPE hibernate-mapping PUBLIC
        "-//Hibernate/Hibernate Mapping DTD//EN"
        "http://www.hibernate.org/dtd/hibernate-mapping-3.0.dtd" >
<hibernate-mapping>
    <class name="${hbmClass.className}" table="${hbmClass.tableName}">
        <id name="${hbmIdColumn.idName}" column="${hbmIdColumn.idColumnName}" type="${hbmIdColumn.type}">
            <generator class="${hbmIdColumn.genClass}"/>
        </id>
    <#list hbmColumns as column>
        <property name="${column.name}" column="${column.columnName}" type="${column.type}"/>
    </#list>
    </class>
</hibernate-mapping>
