package com.ares5k.convert;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 自动生成工具-Java类型及全限定包名Enum类
 *
 * @author ares5k
 * @since 2020-09-16
 * qq: 16891544
 * email: 16891544@qq.com
 */
@Getter
@AllArgsConstructor
public enum JavaTypeInfo {
    //整型
    INTEGER("Integer", null),
    //长整型
    LONG("Long", null),
    //decimal类型
    BIG_DECIMAL("BigDecimal", "java.math.BigDecimal"),
    //布尔类型
    BOOLEAN("Boolean", null),
    //字符类型
    STRING("String", null),
    //日期类型
    DATE("Date", "java.util.Date"),
    //浮点类型
    DOUBLE("Double", null);

    /**
     * 类型名
     */
    private final String typeName;
    /**
     * 类型所在包路径
     */
    private final String packagePath;
}
