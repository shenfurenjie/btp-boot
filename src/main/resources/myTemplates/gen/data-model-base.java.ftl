package ${basePackageName}.model;

import com.alibaba.fastjson.annotation.JSONField;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;




//import org.hibernate.validator.constraints.Length;
//import javax.validation.constraints.*;
//@TableName("${model.tableName}")
@Data
public class ${model.baseClassName} {

    public static final String MODEL_ID = "${model.id}";

    public enum Field {
<#list model.columns as column>
    ${column.name?upper_case}("${column.name}","${column.javaName}","${column.attribute.label!}"),
</#list>


<#list model.relation as rel>
    ${rel.name?upper_case}("${rel.name}",null, null),
</#list>

<#list model.relation as rel>
    ${rel.name?upper_case}_ALL_FIELD("${rel.name}{_all_field}",null, null),
</#list>

        ALL_FIELD("_all_field", null, null);

        @Getter
        @Setter
        String label;
        @Getter
        @Setter
        String name;

        @Getter
        @Setter
        String property;

        Field(String name, String property, String label) {
            this.name = name;
            this.label = label;
            this.property = property;
        }

    }

<#list model.columns as column>
    @JSONField(name = "${column.name}")
    <#if column.name == 'id'>
    @TableId(value = "${column.name}", type = IdType.ID_WORKER)
    <#else >
    @TableField("${column.name}")
    </#if>
    ${column.javaType} ${column.javaName};

</#list>

}
