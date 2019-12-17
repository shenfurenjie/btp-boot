package ${basePackageName}.model;

import com.alibaba.fastjson.annotation.JSONField;
import lombok.Data;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.annotation.TableField;
import java.util.List;


@TableName("${model.tableName}")
@Data
public class ${model.className} extends ${model.baseClassName} {

<#list model.relation as rel>
        @JSONField(name = "${rel.name}")
        @TableField(exist = false)
    <#if rel.array>
        List<${rel.javaTypeName}> ${rel.javaName};
    <#else >
        ${rel.javaTypeName} ${rel.javaName};
    </#if>

</#list>

}
