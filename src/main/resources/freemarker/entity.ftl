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
    <#if !ignoreNull??>
import com.baomidou.mybatisplus.annotation.FieldStrategy;
    </#if>
    <#if insertFillList?? || insertUpdateFillList??>
import com.baomidou.mybatisplus.annotation.FieldFill;
    </#if>
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
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
<#if mybatisPlus??>
@TableName("${tableName}")
</#if>
public class ${fileName} {
<#if columnInfoList??>
<#list columnInfoList as columnInfo>

    /**
     * ${columnInfo.columnComment}
     */
    <#if mybatisPlus??>
        <#if primaryKeyCount == 1>
            <#if columnInfo.primaryKey>
    @TableId(type = IdType.ID_WORKER_STR)
            </#if>
        </#if>
        <#if columnInfo.columnName == 'delFlag'>
    @TableLogic
        </#if>
        <#if columnInfo.columnName == 'version'>
    @Version
        </#if>
    <#if !columnInfo.primaryKey && columnInfo.columnName != 'delFlag' && columnInfo.columnName != 'version'>
        <#if ignoreNull??>
            <#if insertFillList??>
                <#list insertFillList as insertColumn>
                    <#if insertColumn == columnInfo.columnName>
    @TableField(fill = FieldFill.INSERT)
                    </#if>
                </#list>
            </#if>
            <#if insertUpdateFillList??>
                <#list insertUpdateFillList as updateColumn>
                    <#if updateColumn == columnInfo.columnName>
    @TableField(fill = FieldFill.INSERT_UPDATE)
                    </#if>
                </#list>
            </#if>
        </#if>
        <#if !ignoreNull??>
            <#assign hadFieldAnnotation="">
            <#if insertFillList??>
                <#list insertFillList as isnertColumn>
                    <#if isnertColumn == columnInfo.columnName>
                        <#assign hadFieldAnnotation="had">
    @TableField(strategy = FieldStrategy.IGNORED, fill = FieldFill.INSERT)
                    </#if>
                </#list>
            </#if>
            <#if insertUpdateFillList??>
                <#list insertUpdateFillList as updateColumn>
                    <#if updateColumn == columnInfo.columnName>
                        <#assign hadFieldAnnotation="had">
    @TableField(strategy = FieldStrategy.IGNORED, fill = FieldFill.INSERT_UPDATE)
                    </#if>
                </#list>
            </#if>
            <#if hadFieldAnnotation != 'had'>
    @TableField(strategy = FieldStrategy.IGNORED)
            </#if>
        </#if>
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