package com.ares5k.exception;

import lombok.extern.slf4j.Slf4j;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

/**
 * 自动生成工具-统一异常处理类
 *
 * @author ares5k
 * @since 2020-09-16
 * qq: 16891544
 * email: 16891544@qq.com
 */
@Slf4j
@ControllerAdvice
public class GeneratorControllerAdvice {

    /**
     * 自定义IO异常发生时执行
     *
     * @param exception 自定义IO异常
     * @param model     返回给画面的值
     * @return 画面路径
     * @author ares5k
     */
    @ExceptionHandler(value = GeneratorIOException.class)
    public String exception(GeneratorIOException exception, Model model) {
        //日志打印
        log.error("自定义IO异常", exception);
        //画面提交值回显
        model.addAttribute("generatorVo", exception.getGeneratorVo());
        //错误信息
        model.addAttribute("error", "文件生成异常, 请联系作者： 16891544@qq.com");
        //画面路径
        return "index";
    }

    /**
     * 自定义SQL异常发生时执行
     *
     * @param exception 自定义SQL异常
     * @param model     返回给画面的值
     * @return 画面路径
     * @author ares5k
     */
    @ExceptionHandler(value = GeneratorSQLException.class)
    public String exception(GeneratorSQLException exception, Model model) {
        //日志打印
        log.error("自定义SQL异常", exception);
        //画面提交值回显
        model.addAttribute("generatorVo", exception.getGeneratorVo());
        //错误信息
        model.addAttribute("error", "数据库连接异常, 请仔细核对数据源信息!");
        //画面路径
        return "index";
    }

    /**
     * 自定义Template异常发生时执行
     *
     * @param exception 自定义Template异常
     * @param model     返回给画面的值
     * @return 画面路径
     * @author ares5k
     */
    @ExceptionHandler(value = GeneratorTemplateException.class)
    public String exception(GeneratorTemplateException exception, Model model) {
        //日志打印
        log.error("自定义Template异常", exception);
        //画面提交值回显
        model.addAttribute("generatorVo", exception.getGeneratorVo());
        //错误信息
        model.addAttribute("error", "模板解析异常, 请联系作者： 16891544@qq.com");
        //画面路径
        return "index";
    }

    /**
     * 业务未知异常发生时执行
     *
     * @param exception 业务未知异常
     * @param model     返回给画面的值
     * @return 画面路径
     * @author ares5k
     */
    @ExceptionHandler(value = Exception.class)
    public String exception(Exception exception, Model model) {
        //日志打印
        log.error("业务未知异常", exception);
        //错误信息
        model.addAttribute("error", "出先未知异常, 请联系作者： 16891544@qq.com");
        //画面路径
        return "index";
    }
}
