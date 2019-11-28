<column name="${column.columnName}"
        length="${column.length?c}"
        not-null="<#if column.nullable>false<#else >true</#if>"
        unique="<#if column.unique>true<#else >false</#if>"
        <#if column.precision??>precision="${column.precision?c}"</#if>
        <#if column.scale??>scale="${column.scale?c}"</#if>
<#--<#if column.defaultValue??>default="${column.defaultValue}"</#if>-->
>
    <comment>${column.label}</comment>
</column>