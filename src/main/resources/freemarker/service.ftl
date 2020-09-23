<#-- 自动生成 service 模板 -->
<#if pathPattern == "module">
package ${packageName}.${fileNameLower}.service;
</#if>
<#if pathPattern == "ability-and-module">
package ${packageName}.service.${fileNameLower};
</#if>
<#if pathPattern == "ability">
package ${packageName}.service;
</#if>

/**
 * ${tableComment}-Service层
 *
 * @author ${author}
 * @since ${createDate}
 */
public interface I${fileName}Service {

}