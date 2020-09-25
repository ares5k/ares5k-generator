package com.ares5k.service.impl;

import cn.hutool.core.util.StrUtil;
import cn.hutool.core.util.ZipUtil;
import com.ares5k.dao.GeneratorDao;
import com.ares5k.dto.ColumnInfo;
import com.ares5k.dto.TableInfo;
import com.ares5k.exception.GeneratorIOException;
import com.ares5k.exception.GeneratorSQLException;
import com.ares5k.exception.GeneratorTemplateException;
import com.ares5k.service.GeneratorService;
import com.ares5k.template.TemplateData;
import com.ares5k.utils.FreemarkerUtil;
import com.ares5k.utils.PathGeneratorUtil;
import com.ares5k.vo.GeneratorVo;
import freemarker.template.TemplateException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.File;
import java.io.IOException;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

/**
 * 自动生成工具-Service层实现类
 *
 * @author ares5k
 * @since 2020-09-16
 * qq: 16891544
 * email: 16891544@qq.com
 */
@Service
public class GeneratorServiceImpl implements GeneratorService {
    /**
     * 查询数据库中所有表的定义信息
     *
     * @param generatorVo 画面参数接收及响应用
     * @return GeneratorVo 画面参数接收及响应用
     */
    @Override
    public GeneratorVo connectAndGetTables(GeneratorVo generatorVo) {
        try {
            //查询数据库中所有表的定义信息
            generatorVo.setTableInfoList(generatorDao.connectAndGetTables(generatorVo));
        } catch (SQLException exception) {
            throw new GeneratorSQLException(generatorVo, exception);
        }
        return generatorVo;
    }

    /**
     * 查询数据库表中所有字段的定义信息
     *
     * @param generatorVo 画面参数接收及响应用
     * @return zip磁盘路径
     * @author ares5k
     */
    @Override
    public String generator(GeneratorVo generatorVo) {
        //生成文件夹的时间戳
        long timestamp = System.currentTimeMillis();
        //画面选中的需要自动生成的表
        List<String> tableNameList = generatorVo.getCheckedTableNameList();
        try {
            //遍历要自动生成的表集合
            for (String tableName : tableNameList) {
                //文件自动生成
                doGenerator(generatorVo, tableName, timestamp);
            }
        } catch (SQLException exception) {
            //自定义SQL异常
            throw new GeneratorSQLException(generatorVo, exception);
        } catch (IOException exception) {
            //自定义IO异常
            throw new GeneratorIOException(generatorVo, exception);
        } catch (TemplateException exception) {
            //自定义Freemarker Template异常
            throw new GeneratorTemplateException(generatorVo, exception);
        }
        //打Zip包
        ZipUtil.zip(FILE_BASE_PATH + timestamp);
        //zip此怕你路径
        return FILE_BASE_PATH + timestamp;
    }

    /**
     * 文件自动生成
     *
     * @param generatorVo 画面参数接收及响应用
     * @param tableName   字段定义信息集合
     * @throws SQLException      数据库连接异常
     * @throws IOException       文件读写异常
     * @throws TemplateException Freemarker Template解析异常
     * @author ares5k
     */
    private void doGenerator(GeneratorVo generatorVo, String tableName, long timestamp) throws SQLException, IOException, TemplateException {
        //画面选中的需要自动生成的文件类型
        List<String> typeList = generatorVo.getTypeList();
        //遍历要自动生成的文件类型
        for (String type : typeList) {
            //根据文件类型执行不同生成方法
            switch (type) {
                case "entity":
                    //entity文件自动生成
                    entityGenerator(generatorVo, tableName, timestamp);
                    break;
                case "service":
                    //service和serviceImpl文件自动生成
                    serviceGenerator(generatorVo, tableName, timestamp);
                    break;
                case "mapper":
                    //mapper和mapper xml文件自动生成
                    mapperGenerator(generatorVo, tableName, timestamp);
                    break;
                case "controller":
                    //controller自动生成
                    controllerGenerator(generatorVo, tableName, timestamp);
                    break;
            }
        }
    }

    /**
     * controller文件自动生成
     *
     * @param generatorVo 画面参数接收及响应用
     * @param tableName   表名
     * @param timestamp   用时间戳创建第一层目录
     * @throws SQLException      数据库连接异常
     * @throws IOException       文件读写异常
     * @throws TemplateException Freemarker Template解析异常
     * @author ares5k
     */
    private void controllerGenerator(GeneratorVo generatorVo, String tableName, long timestamp) throws SQLException, IOException, TemplateException {
        //生成模板数据
        TemplateData templateData = getTemplate(generatorVo, tableName);
        //生成 controller 文件
        FreemarkerUtil.processTemplate(templateData, "controller.ftl",
                PathGeneratorUtil.controllerPath(
                        timestamp,
                        templateData.getFileName(),
                        templateData.getPackageName(),
                        generatorVo.getPathPattern()));
    }

    /**
     * service和 serviceImpl 文件自动生成
     *
     * @param generatorVo 画面参数接收及响应用
     * @param tableName   表名
     * @param timestamp   用时间戳创建第一层目录
     * @throws SQLException      数据库连接异常
     * @throws IOException       文件读写异常
     * @throws TemplateException Freemarker Template解析异常
     * @author ares5k
     */
    private void serviceGenerator(GeneratorVo generatorVo, String tableName, long timestamp) throws SQLException, IOException, TemplateException {
        //生成模板数据
        TemplateData templateData = getTemplate(generatorVo, tableName);
        //生成 service 文件
        FreemarkerUtil.processTemplate(templateData, "service.ftl",
                PathGeneratorUtil.servicePath(
                        timestamp,
                        templateData.getFileName(),
                        templateData.getPackageName(),
                        generatorVo.getPathPattern()));

        //生成 serviceImpl文件
        FreemarkerUtil.processTemplate(templateData, "serviceImpl.ftl",
                PathGeneratorUtil.serviceImplPath(
                        timestamp,
                        templateData.getFileName(),
                        templateData.getPackageName(),
                        generatorVo.getPathPattern()));
    }

    /**
     * mapper和 mapper xml文件自动生成
     *
     * @param generatorVo 画面参数接收及响应用
     * @param tableName   表名
     * @param timestamp   用时间戳创建第一层目录
     * @throws SQLException      数据库连接异常
     * @throws IOException       文件读写异常
     * @throws TemplateException Freemarker Template解析异常
     * @author ares5k
     */
    private void mapperGenerator(GeneratorVo generatorVo, String tableName, long timestamp) throws SQLException, IOException, TemplateException {
        //生成模板数据
        TemplateData templateData = getTemplate(generatorVo, tableName);
        //生成 mapper 文件
        FreemarkerUtil.processTemplate(templateData, "mapper.ftl",
                PathGeneratorUtil.mapperPath(
                        timestamp,
                        templateData.getFileName(),
                        templateData.getPackageName(),
                        generatorVo.getPathPattern()));

        //生成 mapper xml 文件
        FreemarkerUtil.processTemplate(templateData, "mapperXml.ftl",
                PathGeneratorUtil.mapperXmlPath(
                        timestamp,
                        templateData.getFileName(),
                        generatorVo.getPathPattern()));
    }

    /**
     * entity文件自动生成
     *
     * @param generatorVo 画面参数接收及响应用
     * @param tableName   表名
     * @param timestamp   用时间戳创建第一层目录
     * @throws SQLException      数据库连接异常
     * @throws IOException       文件读写异常
     * @throws TemplateException Freemarker Template解析异常
     * @author ares5k
     */
    private void entityGenerator(GeneratorVo generatorVo, String tableName, long timestamp) throws SQLException, IOException, TemplateException {
        //生成模板数据
        TemplateData templateData = getTemplate(generatorVo, tableName);
        //生成 entity 文件
        FreemarkerUtil.processTemplate(templateData, "entity.ftl",
                PathGeneratorUtil.entityPath(
                        timestamp,
                        templateData.getFileName(),
                        templateData.getPackageName(),
                        generatorVo.getPathPattern()));
    }

    /**
     * 生成 Freemarker模板中需要替换的数据
     *
     * @param generatorVo 画面参数接收及响应用
     * @param tableName   表名
     * @return Freemarker模板中需要替换的数据
     * @throws SQLException 数据库连接异常
     * @author ares5k
     */
    private TemplateData getTemplate(GeneratorVo generatorVo, String tableName) throws SQLException {
        //获取表字段对应的Java信息集合
        List<ColumnInfo> columnInfoList = generatorDao.connectAndGetColumns(generatorVo, tableName);
        //获取需要导入的包
        List<String> importPackageName = columnInfoList.stream().filter(
                columnInfo -> StrUtil.isNotEmpty(columnInfo.getColumnPackage())
        ).map(ColumnInfo::getColumnPackage).distinct().collect(Collectors.toList());

        //Freemarker模板中需要替换的数据
        TemplateData templateData = new TemplateData();
        //是否使用Lombok插件
        templateData.setLombok(generatorVo.getLombok());
        //是否使用Mybatis-Plus插件
        templateData.setMybatisPlus(generatorVo.getMybatisPlus());
        //作者
        templateData.setAuthor(StrUtil.isEmpty(generatorVo.getAuthorName()) ? "ares5k 16891544@qq.com" : generatorVo.getAuthorName());
        //表名备注
        Optional<TableInfo> optional = generatorVo.getTableInfoList().stream().filter(tableInfo -> StrUtil.equals(tableName, tableInfo.getTableName())).findAny();
        optional.ifPresent(tableInfo -> templateData.setTableComment(StrUtil.isEmpty(tableInfo.getTableComment()) ? tableName : tableInfo.getTableComment()));

        //判断表名是否要忽略前缀
        String tableNameNoPreFix = tableName;
        if (StrUtil.isNotEmpty(generatorVo.getIgnorePrefix())) {
            tableNameNoPreFix = StrUtil.removePrefixIgnoreCase(tableName, generatorVo.getIgnorePrefix());
        }

        //文件名首字母小写
        templateData.setFileNameFirstLower(StrUtil.lowerFirst(StrUtil.toCamelCase(tableNameNoPreFix)));
        //文件名全小写
        templateData.setFileNameLower(StrUtil.toCamelCase(tableNameNoPreFix).toLowerCase());
        //文件名
        templateData.setFileName(StrUtil.upperFirst(StrUtil.toCamelCase(tableNameNoPreFix)));
        //基本包名
        templateData.setPackageName(generatorVo.getPackageName());
        //需要导入的包
        templateData.setImportPackageList(importPackageName);
        //路径模式:(按模块划分：module  先按功能, 后按模块划分：ability-and-module   按功能划分：ability)
        templateData.setPathPattern(generatorVo.getPathPattern());
        //创建时间
        templateData.setCreateDate(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        //表字段对应的Java信息集合
        templateData.setColumnInfoList(columnInfoList);
        //是否是联合主键, 联合主键时不使用mybatis-plus自动生成ID
        templateData.setPrimaryKeyCount(columnInfoList.stream().filter(ColumnInfo::isPrimaryKey).count());
        //更新时忽略空值
        templateData.setIgnoreNull(generatorVo.getIgnoreNull());
        return templateData;
    }

    /**
     * 自动生成工具-Dao层
     */
    @Autowired
    private GeneratorDao generatorDao;

    /**
     * 文件的根路径
     */
    private static final String FILE_BASE_PATH = File.separator + "var" + File.separator + "ares5k-generator" + File.separator + "generator-file" + File.separator;
}
