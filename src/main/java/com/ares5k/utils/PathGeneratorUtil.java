package com.ares5k.utils;

import java.io.File;

/**
 * 自动生成工具-生成文件存放路径
 *
 * @author ares5k
 * @since 2020-09-16
 * qq: 16891544
 * email: 16891544@qq.com
 */
public class PathGeneratorUtil {
    /**
     * 文件的根路径
     */
    private static final String FILE_BASE_PATH = File.separator + "var" + File.separator + "ares5k-generator" + File.separator + "generator-file" + File.separator;

    /**
     * 生成Controller文件路径
     *
     * @param timestamp   用时间戳创建第一层目录
     * @param fileName    文件名
     * @param packageName 包名
     * @param pathPattern 路径模式:(按模块划分：module  先按功能, 后按模块划分：ability-and-module   按功能划分：ability)
     * @return Controller文件路径
     * @author ares5k
     */
    public static String controllerPath(long timestamp, String fileName, String packageName, String pathPattern) {
        String path;
        //包路径划分
        switch (pathPattern) {
            //按模块划分
            case "module":
                //example: H:/106548871/com/ares5k/user/controller/UserController.java
                path = FILE_BASE_PATH + timestamp + File.separator
                        + packageName.replace(".", File.separator) + File.separator
                        + fileName.toLowerCase() + File.separator
                        + "controller" + File.separator + fileName + "Controller.java";
                break;
            //先按功能, 后按模块划分
            case "ability-and-module":
                //example: H:/106548871/com/ares5k/controller/user/UserController.java
                path = FILE_BASE_PATH + timestamp + File.separator
                        + packageName.replace(".", File.separator) + File.separator
                        + "controller" + File.separator + fileName.toLowerCase() + File.separator + fileName + "Controller.java";
                break;
            //按功能划分 -默认
            case "ability":
            default:
                //example: H:/106548871/com/ares5k/controller/UserController.java
                path = FILE_BASE_PATH + timestamp + File.separator
                        + packageName.replace(".", File.separator) + File.separator
                        + "controller" + File.separator + fileName + "Controller.java";
                break;
        }
        //Controller文件路径
        return path;
    }

    /**
     * 生成Service接口文件路径
     *
     * @param timestamp   用时间戳创建第一层目录
     * @param fileName    文件名
     * @param packageName 包名
     * @param pathPattern 路径模式:(按模块划分：module  先按功能, 后按模块划分：ability-and-module   按功能划分：ability)
     * @return Service接口文件路径
     * @author ares5k
     */
    public static String servicePath(long timestamp, String fileName, String packageName, String pathPattern) {
        String path;
        //包路径划分
        switch (pathPattern) {
            //按模块划分
            case "module":
                //example: H:/106548871/com/ares5k/user/service/IUserService.java
                path = FILE_BASE_PATH + timestamp + File.separator
                        + packageName.replace(".", File.separator) + File.separator
                        + fileName.toLowerCase() + File.separator
                        + "service" + File.separator + "I" + fileName + "Service.java";
                break;
            //先按功能, 后按模块划分
            case "ability-and-module":
                //example: H:/106548871/com/ares5k/service/user/IUserService.java
                path = FILE_BASE_PATH + timestamp + File.separator
                        + packageName.replace(".", File.separator) + File.separator
                        + "service" + File.separator + fileName.toLowerCase() + File.separator + "I" + fileName + "Service.java";
                break;
            //按功能划分 -默认
            case "ability":
            default:
                //example: H:/106548871/com/ares5k/service/IUserService.java
                path = FILE_BASE_PATH + timestamp + File.separator
                        + packageName.replace(".", File.separator) + File.separator
                        + "service" + File.separator + "I" + fileName + "Service.java";
                break;
        }
        //Service接口文件路径
        return path;
    }

    /**
     * 生成Service实现类文件路径
     *
     * @param timestamp   用时间戳创建第一层目录
     * @param fileName    文件名
     * @param packageName 包名
     * @param pathPattern 路径模式:(按模块划分：module  先按功能, 后按模块划分：ability-and-module   按功能划分：ability)
     * @return Service实现类文件路径
     * @author ares5k
     */
    public static String serviceImplPath(long timestamp, String fileName, String packageName, String pathPattern) {
        String path;
        //包路径划分
        switch (pathPattern) {
            //按模块划分
            case "module":
                //example: H:/106548871/com/ares5k/user/service/impl/IUserService.java
                path = FILE_BASE_PATH + timestamp + File.separator
                        + packageName.replace(".", File.separator) + File.separator
                        + fileName.toLowerCase() + File.separator
                        + "service" + File.separator + "impl" + File.separator + fileName + "ServiceImpl.java";
                break;
            //先按功能, 后按模块划分
            case "ability-and-module":
                //example: H:/106548871/com/ares5k/service/impl/user/UserServiceImpl.java
                path = FILE_BASE_PATH + timestamp + File.separator
                        + packageName.replace(".", File.separator) + File.separator
                        + "service" + File.separator + "impl" + File.separator + fileName.toLowerCase()
                        + File.separator + fileName + "ServiceImpl.java";
                break;
            //按功能划分 -默认
            case "ability":
            default:
                //example: H:/106548871/com/ares5k/service/impl/UserServiceImpl.java
                path = FILE_BASE_PATH + timestamp + File.separator
                        + packageName.replace(".", File.separator) + File.separator
                        + "service" + File.separator + "impl" + File.separator + fileName + "ServiceImpl.java";
                break;
        }
        //Service实现类文件路径
        return path;
    }

    /**
     * 生成Mapper接口文件路径
     *
     * @param timestamp   用时间戳创建第一层目录
     * @param fileName    文件名
     * @param packageName 包名
     * @param pathPattern 路径模式:(按模块划分：module  先按功能, 后按模块划分：ability-and-module   按功能划分：ability)
     * @return Mapper接口文件路径
     * @author ares5k
     */
    public static String mapperPath(long timestamp, String fileName, String packageName, String pathPattern) {
        String path;
        //包路径划分
        switch (pathPattern) {
            //按模块划分
            case "module":
                //example: H:/106548871/com/ares5k/user/mapper/UserMapper.java
                path = FILE_BASE_PATH + timestamp + File.separator
                        + packageName.replace(".", File.separator) + File.separator
                        + fileName.toLowerCase() + File.separator
                        + "mapper" + File.separator + fileName + "Mapper.java";
                break;
            //先按功能, 后按模块划分
            case "ability-and-module":
                //example: H:/106548871/com/ares5k/mapper/user/UserMapper.java
                path = FILE_BASE_PATH + timestamp + File.separator
                        + packageName.replace(".", File.separator) + File.separator
                        + "mapper" + File.separator + fileName.toLowerCase() + File.separator + fileName + "Mapper.java";
                break;
            //按功能划分 -默认
            case "ability":
            default:
                //example: H:/106548871/com/ares5k/mapper/UserMapper.java
                path = FILE_BASE_PATH + timestamp + File.separator
                        + packageName.replace(".", File.separator) + File.separator
                        + "mapper" + File.separator + fileName + "Mapper.java";
                break;
        }
        //Mapper接口文件路径
        return path;
    }

    /**
     * 生成Mapper xml文件路径
     *
     * @param timestamp   用时间戳创建第一层目录
     * @param fileName    文件名
     * @param pathPattern 路径模式:(按模块划分：module  先按功能, 后按模块划分：ability-and-module   按功能划分：ability)
     * @return Mapper xml文件路径
     * @author ares5k
     */
    public static String mapperXmlPath(long timestamp, String fileName, String pathPattern) {
        String path;
        //包路径划分
        switch (pathPattern) {
            //按模块划分
            case "module":
                //example: H:/106548871/resources/user/mapper/UserMapper.xml
                path = FILE_BASE_PATH + timestamp + File.separator
                        + "resources" + File.separator + fileName.toLowerCase() + File.separator
                        + "mapper" + File.separator + fileName + "Mapper.xml";
                break;
            //先按功能, 后按模块划分
            case "ability-and-module":
                //example: H:/106548871/resources/mapper/user/UserMapper.xml
                path = FILE_BASE_PATH + timestamp + File.separator
                        + "resources" + File.separator + "mapper" + File.separator + fileName.toLowerCase() + File.separator + fileName + "Mapper.xml";
                break;
            //按功能划分 -默认
            case "ability":
            default:
                //example: H:/106548871/resources/mapper/UserMapper.xml
                path = FILE_BASE_PATH + timestamp + File.separator
                        + "resources" + File.separator + "mapper" + File.separator + fileName + "Mapper.xml";
                break;
        }
        //Mapper xml文件路径
        return path;
    }

    /**
     * 生成Entity文件路径
     *
     * @param timestamp   用时间戳创建第一层目录
     * @param fileName    文件名
     * @param packageName 包名
     * @param pathPattern 路径模式:(按模块划分：module  先按功能, 后按模块划分：ability-and-module   按功能划分：ability)
     * @return entity文件路径
     * @author ares5k
     */
    public static String entityPath(long timestamp, String fileName, String packageName, String pathPattern) {
        String path;
        //包路径划分
        switch (pathPattern) {
            //按模块划分
            case "module":
                //example: H:/106548871/com/ares5k/user/entity/User.java
                path = FILE_BASE_PATH + timestamp + File.separator
                        + packageName.replace(".", File.separator) + File.separator
                        + fileName.toLowerCase() + File.separator
                        + "entity" + File.separator + fileName + ".java";
                break;
            //先按功能, 后按模块划分
            case "ability-and-module":
                //example: H:/106548871/com/ares5k/entity/user/User.java
                path = FILE_BASE_PATH + timestamp + File.separator
                        + packageName.replace(".", File.separator) + File.separator
                        + "entity" + File.separator + fileName.toLowerCase() + File.separator + fileName + ".java";
                break;
            //按功能划分 -默认
            case "ability":
            default:
                //example: H:/106548871/com/ares5k/entity/User.java
                path = FILE_BASE_PATH + timestamp + File.separator
                        + packageName.replace(".", File.separator) + File.separator
                        + "entity" + File.separator + fileName + ".java";
                break;
        }
        //entity文件路径
        return path;
    }
}
