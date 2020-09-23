package com.ares5k.dto;

import lombok.Getter;
import lombok.Setter;

/**
 * 自动生成工具-保存数据库连接信息用
 *
 * @author ares5k
 * @since 2020-09-16
 * qq: 16891544
 * email: 16891544@qq.com
 */
@Getter
@Setter
public class DBInfo {
    /**
     * 主机名
     */
    private String hostname;
    /**
     * 数据库名
     */
    private String dbName;
    /**
     * 用户名
     */
    private String dbUser;
    /**
     * 密码
     */
    private String dbPwd;
    /**
     * 端口
     */
    private String port;
}
