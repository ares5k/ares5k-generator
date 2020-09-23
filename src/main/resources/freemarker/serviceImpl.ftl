<#-- 自动生成 service 模板 -->
<#if pathPattern == "module">
package ${packageName}.${fileNameLower}.service.impl;

</#if>
<#if pathPattern == "ability-and-module">
package ${packageName}.service.impl.${fileNameLower};

</#if>
<#if pathPattern == "ability">
package ${packageName}.service.impl;

</#if>
<#if pathPattern == "module">
    <#if mybatisPlus??>
import ${packageName}.${fileNameLower}.entity.${fileName};
    </#if>
import ${packageName}.${fileNameLower}.mapper.${fileName}Mapper;
import ${packageName}.${fileNameLower}.service.I${fileName}Service;
</#if>
<#if pathPattern == "ability-and-module">
    <#if mybatisPlus??>
import ${packageName}.entity.${fileNameLower}.${fileName};
    </#if>
import ${packageName}.mapper.${fileNameLower}.${fileName}Mapper;
import ${packageName}.service.${fileNameLower}.I${fileName}Service;
</#if>
<#if pathPattern == "ability">
    <#if mybatisPlus??>
import ${packageName}.entity.${fileName};
    </#if>
import ${packageName}.mapper.${fileName}Mapper;
import ${packageName}.service.I${fileName}Service;
</#if>
<#if mybatisPlus??>
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
</#if>
<#if lombok??>
import lombok.extern.slf4j.Slf4j;
</#if>
<#if !mybatisPlus??>
import org.springframework.beans.factory.annotation.Autowired;
</#if>
import org.springframework.stereotype.Service;

/**
 * ${tableComment}-Service 实现层
 *
 * @author ${author}
 * @since ${createDate}
 */
<#if lombok??>
@Slf4j
</#if>
@Service
public class ${fileName}ServiceImpl <#if mybatisPlus??>extends ServiceImpl<${fileName}Mapper, ${fileName}> </#if>implements I${fileName}Service {

<#if !mybatisPlus??>
    /**
     * ${tableComment}-Mapper层
     */
    @Autowired
    private ${fileName}Mapper ${fileNameFirstLower}Mapper;

</#if>
}