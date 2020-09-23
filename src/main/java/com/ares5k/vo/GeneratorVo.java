package com.ares5k.vo;

import com.ares5k.dto.DBInfo;
import com.ares5k.dto.TableInfo;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * 自动生成工具-画面参数接收及响应用
 *
 * @author ares5k
 * @since 2020-09-16
 * qq: 16891544
 * email: 16891544@qq.com
 */
@Getter
@Setter
@Accessors(chain = true)
public class GeneratorVo extends DBInfo {
    /**
     * 生成者
     */
    private String authorName;

    /**
     * 包名
     */
    private String packageName;

    /**
     * 需要生成文件的类型: controller/service/mapper/entity
     */
    private List<String> typeList;

    /**
     * 是否使用了Lombok插件
     */
    private String lombok;

    /**
     * 是否使用了Mybatis-Plus插件
     */
    private String mybatisPlus;

    /**
     * 路径模式:(按模块划分：module  先按功能, 后按模块划分：ability-and-module   按功能划分：ability)
     */
    private String pathPattern;

    /**
     * 生成文件和包时, 忽略的表名前缀
     */
    private String ignorePrefix;

    /**
     * 画面选中的表名-即需要自动生成代码的表
     */
    private List<String> checkedTableNameList;

    /**
     * 数据库中表定义信息集合
     */
    private List<TableInfo> tableInfoList;
}
