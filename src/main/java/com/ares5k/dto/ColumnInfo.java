package com.ares5k.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 自动生成工具-保存表字段对应的Java信息用
 *
 * @author ares5k
 * @since 2020-09-16
 * qq: 16891544
 * email: 16891544@qq.com
 */
@Getter
@Setter
public class ColumnInfo {

    /**
     * 是否是主键
     */
    private boolean primaryKey;

    /**
     * 表字段名 -> java属性 驼峰
     */
    private String columnName;

    /**
     * 表字段名 -> java属性 驼峰 首字母大写
     */
    private String columnNameFirstUpper;

    /**
     * 表字段类型 -> java类型
     */
    private String columnType;

    /**
     * java类型的包全限定名
     */
    private String columnPackage;

    /**
     * 字段备注
     */
    private String columnComment;
}
