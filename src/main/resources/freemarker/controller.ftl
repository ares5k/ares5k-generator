<#-- 自动生成 controller 模板 -->
<#if pathPattern == "module">
package ${packageName}.${fileNameLower}.controller;

</#if>
<#if pathPattern == "ability-and-module">
package ${packageName}.controller.${fileNameLower};

</#if>
<#if pathPattern == "ability">
package ${packageName}.controller;

</#if>
<#if pathPattern == "module">
import ${packageName}.${fileNameLower}.service.I${fileName}Service;
</#if>
<#if pathPattern == "ability-and-module">
import ${packageName}.service.${fileNameLower}.I${fileName}Service;
</#if>
<#if pathPattern == "ability">
import ${packageName}.service.I${fileName}Service;
</#if>
<#if lombok??>
import lombok.extern.slf4j.Slf4j;
</#if>
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * ${tableComment}-Controller层
 *
 * @author ${author}
 * @since ${createDate}
 */
<#if lombok??>
@Slf4j
</#if>
@Controller
@RequestMapping("/${fileNameFirstLower}")
public class ${fileName}Controller {

    /**
     * ${tableComment}-Service层
     */
    @Autowired
    private I${fileName}Service ${fileNameFirstLower}Service;
}