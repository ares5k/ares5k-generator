package com.ares5k.dao;

import com.ares5k.dto.ColumnInfo;
import com.ares5k.dto.DBInfo;
import com.ares5k.dto.TableInfo;

import java.sql.SQLException;
import java.util.List;

/**
 * 自动生成工具-Dao层
 *
 * @author ares5k
 * @since 2020-09-16
 * qq: 16891544
 * email: 16891544@qq.com
 */
public interface GeneratorDao {
    /**
     * 查询数据库中所有表的定义信息
     *
     * @param dbInfo 数据库连接信息
     * @return 表定义信息集合
     * @throws SQLException 数据库连接异常
     * @author ares5k
     */
    List<TableInfo> connectAndGetTables(DBInfo dbInfo) throws SQLException;

    /**
     * 查询数据库表中所有字段的定义信息
     *
     * @param dbInfo    数据库连接信息
     * @param tableName 表名
     * @return 字段定义信息集合
     * @throws SQLException 数据库连接异常
     * @author ares5k
     */
    List<ColumnInfo> connectAndGetColumns(DBInfo dbInfo, String tableName) throws SQLException;
}
