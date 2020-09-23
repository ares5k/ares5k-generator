<#-- 自动生成 entity 模板 -->
<#if pathPattern == "module">
package ${packageName}.${fileNameLower}.entity;

</#if>
<#if pathPattern == "ability-and-module">
package ${packageName}.entity.${fileNameLower};

</#if>
<#if pathPattern == "ability">
package ${packageName}.entity;

</#if>
<#if mybatisPlus??>
    <#if primaryKeyCount == 1>
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
    </#if>
import com.baomidou.mybatisplus.annotation.TableLogic;
import com.baomidou.mybatisplus.annotation.Version;
</#if>
<#if lombok??>
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;
</#if>
<#if mybatisPlus?? || lombok??>

</#if>
<#if importPackageList??>
    <#list importPackageList as pkg>
        <#if pkg??>
import ${pkg};
        </#if>
    </#list>
</#if>

/**
 * ${tableComment}
 *
 * @author ${author}
 * @since ${createDate}
 */
<#if lombok??>
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
</#if>
public class ${fileName} {
<#if columnInfoList??>
<#list columnInfoList as columnInfo>

    /**
     * ${columnInfo.columnComment}
     */
    <#if mybatisPlus??>
        <#if primaryKeyCount == 1>
            <#if columnInfo.primaryKey == true>
    @TableId(type = IdType.ID_WORKER_STR)
            </#if>
        </#if>
    </#if>
    <#if mybatisPlus??>
        <#if columnInfo.columnName == 'delFlag'>
    @TableLogic
        </#if>
        <#if columnInfo.columnName == 'version'>
    @Version
        </#if>
    </#if>
    private ${columnInfo.columnType} ${columnInfo.columnName};
</#list>
</#if>

<#if !lombok??>
    <#if columnInfoList??>
        <#list columnInfoList as columnInfo>
    /**
     * 设置${columnInfo.columnComment}属性
     *
     * @param ${columnInfo.columnName} ${columnInfo.columnComment}
     * @author ${author}
     */
    public void set${columnInfo.columnNameFirstUpper}(${columnInfo.columnType} ${columnInfo.columnName}) {
        this.${columnInfo.columnName} = ${columnInfo.columnName};
    }

    /**
     * 获取${columnInfo.columnComment}属性值
     *
     * @return ${columnInfo.columnType} ${columnInfo.columnComment}
     * @author ${author}
     */
            <#if columnInfo.columnType != "Boolean">
    public ${columnInfo.columnType} get${columnInfo.columnNameFirstUpper}() {
            </#if>
            <#if columnInfo.columnType == "Boolean">
    public ${columnInfo.columnType} is${columnInfo.columnNameFirstUpper}() {
            </#if>
        return this.${columnInfo.columnName};
    }

        </#list>
    </#if>
</#if>
}