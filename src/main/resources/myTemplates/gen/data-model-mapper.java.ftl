package ${basePackageName}.mapper;
import org.apache.ibatis.annotations.Mapper;
import ${basePackageName}.model.${model.className};

/**
 * <p>
    * ${model.id!} Mapper 接口
    * </p>
 *
 */
@Mapper
public interface ${model.mapperName} extends ${superMapperClass}<${model.className}> {}