package ${basePackageName}.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;




//import com.jossv.framework.gql.runtime.dto.QueryResult;
//@TableName("${model.tableName}")
@Data
public class ${model.className} extends ${model.baseClassName} {

<#list model.relation as rel>
        @JSONField(name = "${rel.name}")
    <#if rel.array>
        List<${rel.javaTypeName}> ${rel.javaName};
    <#else >
        ${rel.javaTypeName} ${rel.javaName};
    </#if>

</#list>

}
