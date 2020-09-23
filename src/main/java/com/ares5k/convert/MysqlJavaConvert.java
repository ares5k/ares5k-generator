package com.ares5k.convert;

/**
 * 自动生成工具-Mysql类型->Java类型转换类
 *
 * @author ares5k
 * @since 2020-09-16
 * qq: 16891544
 * email: 16891544@qq.com
 */
public class MysqlJavaConvert {

    /**
     * Mysql类型->Java类型转换类
     *
     * @param dbType Mysql类型
     * @return JavaTypeInfo Java类型及全限定包名Enum类
     * @author ares5k
     */
    public static JavaTypeInfo convert(String dbType) {
        JavaTypeInfo javaTypeInfo;
        switch (dbType.toLowerCase()) {
            //整型
            case "int":
            case "integer":
            case "tinyint":
            case "smallint":
            case "mediumint":
            case "year":
                javaTypeInfo = JavaTypeInfo.INTEGER;
                break;

            //长整型
            case "bigint":
            case "timestamp":
                javaTypeInfo = JavaTypeInfo.LONG;
                break;

            //decimal类型
            case "decimal":
            case "numeric":
                javaTypeInfo = JavaTypeInfo.BIG_DECIMAL;
                break;

            //浮点类型
            case "float":
            case "double":
                javaTypeInfo = JavaTypeInfo.DOUBLE;
                break;

            //布尔类型
            case "bool":
            case "boolean":
                javaTypeInfo = JavaTypeInfo.BOOLEAN;
                break;

            //日期类型
            case "time":
            case "date":
            case "datetime":
                javaTypeInfo = JavaTypeInfo.DATE;
                break;

            //字符类型
            case "char":
            case "varchar":
            default:
                javaTypeInfo = JavaTypeInfo.STRING;
                break;
        }
        return javaTypeInfo;
    }
}
