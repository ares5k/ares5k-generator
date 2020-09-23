package com.ares5k.dao.impl;

import cn.hutool.core.util.StrUtil;
import com.ares5k.convert.MysqlJavaConvert;
import com.ares5k.dao.GeneratorDao;
import com.ares5k.dto.ColumnInfo;
import com.ares5k.dto.DBInfo;
import com.ares5k.dto.TableInfo;
import com.ares5k.utils.JdbcUtil;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * 自动生成工具-Dao层实现类
 *
 * @author ares5k
 * @since 2020-09-16
 * qq: 16891544
 * email: 16891544@qq.com
 */
@Repository
public class GeneratorDaoImpl implements GeneratorDao {
    /**
     * 查询数据库中所有表的定义信息
     *
     * @param dbInfo 数据库连接信息
     * @return 表定义信息集合
     * @throws SQLException 数据库连接异常
     * @author ares5k
     */
    @Override
    public List<TableInfo> connectAndGetTables(DBInfo dbInfo) throws SQLException {
        //获取数据库连接
        Connection connection = JdbcUtil.getConnection(dbInfo);
        //获取数据库中全部表的SQL
        PreparedStatement preparedStatement = connection.prepareStatement(GET_TABLES_SQL);
        //替换SQL中的占位符
        preparedStatement.setString(1, dbInfo.getDbName());
        //执行SQL语句
        ResultSet resultSet = preparedStatement.executeQuery();
        List<TableInfo> tableInfoList = new ArrayList<>();
        //从结果集中获取所有表信息
        while (resultSet.next()) {
            TableInfo tableInfo = new TableInfo();
            tableInfo.setTableName(resultSet.getString("TABLE_NAME"));
            tableInfo.setTableComment(resultSet.getString("TABLE_COMMENT"));
            tableInfoList.add(tableInfo);
        }
        //关闭数据库连接
        JdbcUtil.closeConnection(resultSet, preparedStatement, connection);
        return tableInfoList;
    }


    /**
     * 查询数据库表中所有字段的定义信息
     *
     * @param dbInfo    数据库连接信息
     * @param tableName 表名
     * @return 字段定义信息集合
     * @throws SQLException 数据库连接异常
     * @author ares5k
     */
    @Override
    public List<ColumnInfo> connectAndGetColumns(DBInfo dbInfo, String tableName) throws SQLException {
        //获取数据库连接
        Connection connection = JdbcUtil.getConnection(dbInfo);
        //获取数据库表中全部字段的SQL
        PreparedStatement preparedStatement = connection.prepareStatement(GET_COLUMNS_SQL);
        //替换SQL中的占位符
        preparedStatement.setString(1, dbInfo.getDbName());
        preparedStatement.setString(2, tableName);
        //执行SQL语句
        ResultSet resultSet = preparedStatement.executeQuery();
        List<ColumnInfo> columnInfoList = new ArrayList<>();
        //从结果集中获取所有字段信息
        while (resultSet.next()) {
            //保存表字段对应的Java信息
            ColumnInfo columnInfo = new ColumnInfo();
            //获取字段名并转换成Java驼峰
            columnInfo.setColumnName(StrUtil.toCamelCase(resultSet.getString("COLUMN_NAME")));
            //获取字段名并转换成Java驼峰-首字母大写
            columnInfo.setColumnNameFirstUpper(StrUtil.upperFirst(StrUtil.toCamelCase(resultSet.getString("COLUMN_NAME"))));
            //获取字段名并转换成Java类型
            columnInfo.setColumnType(MysqlJavaConvert.convert(resultSet.getString("DATA_TYPE")).getTypeName());
            //获取Java类型包全限定名
            columnInfo.setColumnPackage(MysqlJavaConvert.convert(resultSet.getString("DATA_TYPE")).getPackagePath());
            //获取字段备注
            columnInfo.setColumnComment(StrUtil.isEmpty(resultSet.getString("COLUMN_COMMENT"))
                    ? resultSet.getString("COLUMN_NAME") : resultSet.getString("COLUMN_COMMENT"));
            //是否是主键
            columnInfo.setPrimaryKey(StrUtil.isNotEmpty(resultSet.getString("COLUMN_KEY")));
            columnInfoList.add(columnInfo);
        }
        return columnInfoList;
    }

    /**
     * 获取数据库中全部表的SQL
     */
    private static final String GET_TABLES_SQL = "SELECT TABLE_NAME, TABLE_COMMENT FROM INFORMATION_SCHEMA.TABLES WHERE TABLE_SCHEMA = ? AND TABLE_TYPE = 'BASE TABLE';";
    /**
     * 获取数据库表中全部字段的SQL
     */
    private static final String GET_COLUMNS_SQL = "SELECT COLUMN_KEY, COLUMN_NAME, DATA_TYPE, COLUMN_COMMENT FROM INFORMATION_SCHEMA.COLUMNS WHERE TABLE_SCHEMA = ? AND TABLE_NAME = ?";
}
