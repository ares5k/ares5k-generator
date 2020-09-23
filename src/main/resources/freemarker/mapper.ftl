<#-- 自动生成 mapper 模板 -->
<#if pathPattern == "module">
package ${packageName}.${fileNameLower}.mapper;

</#if>
<#if pathPattern == "ability-and-module">
package ${packageName}.mapper.${fileNameLower};

</#if>
<#if pathPattern == "ability">
package ${packageName}.mapper;

</#if>
<#if mybatisPlus??>
    <#if pathPattern == "module">
import ${packageName}.${fileNameLower}.entity.${fileName};
    </#if>
    <#if pathPattern == "ability-and-module">
import ${packageName}.entity.${fileNameLower}.${fileName};
    </#if>
    <#if pathPattern == "ability">
import ${packageName}.entity.${fileName};
    </#if>
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
</#if>
import org.apache.ibatis.annotations.Mapper;

/**
 * ${tableComment}-Mapper层
 *
 * @author ${author}
 * @since ${createDate}
 */
@Mapper
public interface ${fileName}Mapper <#if mybatisPlus??>extends BaseMapper<${fileName}> </#if>{

}