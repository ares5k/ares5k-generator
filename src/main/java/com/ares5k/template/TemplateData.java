package com.ares5k.template;

import com.ares5k.dto.ColumnInfo;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

/**
 * 自动生成工具-Freemarker模板中需要替换的数据
 *
 * @author ares5k
 * @since 2020-09-16
 * qq: 16891544
 * email: 16891544@qq.com
 */
@Getter
@Setter
public class TemplateData {

    /**
     * 表中主键数量, 如果>1代表联合主键, 联合主键时不使用mybatis-plus自动生成ID
     */
    private long primaryKeyCount;

    /**
     * 是否使用Lombok插件
     */
    private String lombok;

    /**
     * 是否使用Mybatis-plus插件
     */
    private String mybatisPlus;

    /**
     * 文件名 驼峰
     */
    private String fileName;

    /**
     * 文件名-驼峰 首字母小写
     */
    private String fileNameFirstLower;

    /**
     * 文件名-全小写
     */
    private String fileNameLower;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 表名备注
     */
    private String tableComment;

    /**
     * 作者名
     */
    private String author;

    /**
     * 创建时间
     */
    private String createDate;

    /**
     * 路径模式:(按模块划分：module  先按功能, 后按模块划分：ability-and-module   按功能划分：ability)
     */
    private String pathPattern;

    /**
     * 表字段对应的Java信息集合
     */
    private List<ColumnInfo> columnInfoList;

    /**
     * 需要导入的包
     */
    private List<String> importPackageList;
}
