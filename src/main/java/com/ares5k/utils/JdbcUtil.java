package com.ares5k.utils;

import cn.hutool.core.util.ObjectUtil;
import com.ares5k.dto.DBInfo;

import java.sql.*;

/**
 * 自动生成工具-数据库连接工具类
 *
 * @author ares5k
 * @since 2020-09-16
 * qq: 16891544
 * email: 16891544@qq.com
 */
public class JdbcUtil {
    /**
     * 获取数据库连接
     *
     * @param dbInfo 数据库连接信息
     * @return 数据库连接
     * @throws SQLException 数据库连接异常
     * @author ares5k
     */
    public static Connection getConnection(DBInfo dbInfo) throws SQLException {
        return DriverManager.getConnection(String.format(url_format, dbInfo.getHostname(), dbInfo.getPort(), dbInfo.getDbName()),
                dbInfo.getDbUser(),
                dbInfo.getDbPwd());
    }

    /**
     * 关闭数据库连接
     *
     * @param resultSet  结果集
     * @param statement  执行SQL语句对象
     * @param connection 数据库连接
     * @throws SQLException 数据库连接关闭异常
     * @author ares5k
     */
    public static void closeConnection(ResultSet resultSet, Statement statement, Connection connection) throws SQLException {
        //结果集不为空
        if (ObjectUtil.isNotEmpty(resultSet)) {
            resultSet.close();
        }
        //执行SQL语句对象不为空
        if (ObjectUtil.isNotEmpty(statement)) {
            statement.close();
        }
        //数据库连接
        if (ObjectUtil.isNotEmpty(connection)) {
            connection.close();
        }
    }

    //数据库连接格式
    private static final String url_format = "jdbc:mysql://%s:%s/%s?serverTimezone=Asia/Shanghai&useUnicode=true&characterEncoding=UTF-8&allowMultiQueries=true&useSSL=false";
}
