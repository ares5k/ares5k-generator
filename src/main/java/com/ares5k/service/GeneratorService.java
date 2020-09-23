package com.ares5k.service;

import com.ares5k.vo.GeneratorVo;

/**
 * 自动生成工具-Service层
 *
 * @author ares5k
 * @since 2020-09-16
 * qq: 16891544
 * email: 16891544@qq.com
 */
public interface GeneratorService {
    /**
     * 查询数据库中所有表的定义信息
     *
     * @param generatorVo 画面参数接收及响应用
     * @return GeneratorVo 画面参数接收及响应用
     */
    GeneratorVo connectAndGetTables(GeneratorVo generatorVo);

    /**
     * 查询数据库表中所有字段的定义信息
     *
     * @param generatorVo 画面参数接收及响应用
     * @return zip磁盘路径
     * @author ares5k
     */
    String generator(GeneratorVo generatorVo);
}
