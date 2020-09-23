<#-- 自动生成 mapper xml 模板 -->
<?xml version="1.0" encoding="UTF-8" ?>
<!DOCTYPE mapper PUBLIC "-//mybatis.org//DTD Mapper 3.0//EN" "http://mybatis.org/dtd/mybatis-3-mapper.dtd">

<!-- ${tableComment}
    @author ${author}
    @since ${createDate} -->
<#if pathPattern == "module">
<mapper namespace="${packageName}.${fileNameLower}.mapper.${fileName}Mapper">

</mapper>
</#if>
<#if pathPattern == "ability-and-module">
<mapper namespace="${packageName}.mapper.${fileNameLower}.${fileName}Mapper">

</mapper>
</#if>
<#if pathPattern == "ability">
<mapper namespace="${packageName}.mapper.${fileName}Mapper">

</mapper>
</#if>

