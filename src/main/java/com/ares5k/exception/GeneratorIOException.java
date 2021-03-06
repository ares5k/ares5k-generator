package com.ares5k.exception;

import com.ares5k.vo.GeneratorVo;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

/**
 * 自动生成工具-自定义IO异常类
 *
 * @author ares5k
 * @since 2020-09-16
 * qq: 16891544
 * email: 16891544@qq.com
 */
@AllArgsConstructor
public class GeneratorIOException extends RuntimeException {

    /**
     * 保存画面提交参数, 回显用
     */
    @Getter
    @Setter
    private GeneratorVo generatorVo;

    /**
     * 构造重载
     *
     * @param generatorVo 保存画面提交参数, 回显用
     * @param throwable   根异常
     * @author ares5k
     */
    public GeneratorIOException(GeneratorVo generatorVo, Throwable throwable) {
        super(throwable);
        this.generatorVo = generatorVo;
    }

    /**
     * 构造重载-自定义错误信息
     *
     * @param generatorVo 保存画面提交参数, 回显用
     * @param throwable   根异常
     * @author ares5k
     */
    public GeneratorIOException(GeneratorVo generatorVo, Throwable throwable, String message) {
        super(message, throwable);
        this.generatorVo = generatorVo;
    }
}
