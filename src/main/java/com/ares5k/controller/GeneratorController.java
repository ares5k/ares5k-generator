package com.ares5k.controller;

import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import com.ares5k.exception.GeneratorIOException;
import com.ares5k.service.GeneratorService;
import com.ares5k.vo.GeneratorVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.http.HttpServletResponse;
import java.io.OutputStream;

/**
 * 自动生成工具-Controller层
 *
 * @author ares5k
 * @since 2020-09-16
 * qq: 16891544
 * email: 16891544@qq.com
 */
@Controller
@RequestMapping("/")
public class GeneratorController {

    /**
     * 工程默认路径-返回自动生成工具主页
     *
     * @return Freemarker模板所在路径
     * @author ares5k
     */
    @GetMapping
    public String index() {
        return "index";
    }

    /**
     * 取得指定数据源中所有表
     *
     * @param generatorVo 画面提交参数
     * @param model       返回给画面的值
     * @return 画面路径
     * @author ares5k
     */
    @PostMapping("/tables")
    public String tables(GeneratorVo generatorVo, Model model) {
        //参数校验
        String message = checkConnectParam(generatorVo);
        //清空画面前一回选择的表名集合
        generatorVo.setCheckedTableNameList(null);
        //校验失败
        if (StrUtil.isNotEmpty(message)) {
            //设置错误信息
            model.addAttribute("error", message);
            //画面值反显
            model.addAttribute("generatorVo", generatorVo);
        } else {
            //查询数据库中所有表的定义信息
            model.addAttribute("generatorVo", generatorService.connectAndGetTables(generatorVo));
        }
        return "index";
    }

    /**
     * 生成代码并下载
     *
     * @param generatorVo 画面提交参数
     * @param model       返回给画面的值
     * @param response    servlet响应-Zip下载
     * @return 正常时无返回，异常时返回画面路径
     * @author ares5k
     */
    @PostMapping("/download")
    public String download(GeneratorVo generatorVo, Model model, HttpServletResponse response) {
        //参数校验
        String message = checkDownloadParam(generatorVo);
        //校验失败
        if (StrUtil.isNotEmpty(message)) {
            //设置错误信息
            model.addAttribute("error", message);
            //画面值反显
            model.addAttribute("generatorVo", generatorVo);
        } else {
            //自动生成文件，并获取 Zip的磁盘路径
            String path = generatorService.generator(generatorVo);

            //设置ServletResponse
            response.setHeader("Content-Disposition", "attachment;filename=ares5k-generator.zip");
            try {
                //下载 zip
                OutputStream outputStream = response.getOutputStream();
                outputStream.write(FileUtil.readBytes(path + ".zip"));
                outputStream.flush();
                outputStream.close();
            } catch (Exception exception) {
                //重置Response
                //防止java.lang.IllegalStateException: getOutputStream() has already been called for this response异常
                response.reset();
                throw new GeneratorIOException(generatorVo, exception, "响应时异常, 请联系作者： 16891544@qq.com");
            }
            //删除磁盘文件和 zip
            FileUtil.del(path);
            FileUtil.del(path + ".zip");
            //执行直接下载
            return null;
        }
        //校验失败返回页面
        return "index";
    }

    /**
     * 校验-取得指定数据源中所有表和生成代码并下载方法的共通参数校验
     *
     * @param generatorVo 画面提交参数
     * @return 错误信息
     * @author ares5k
     */
    private String checkConnectParam(GeneratorVo generatorVo) {
        String message = "";
        //参数获取异常
        if (ObjectUtil.isEmpty(generatorVo)) {
            message = "参数获取异常";
        }
        //主机名不能为空
        if (StrUtil.isEmpty(message) && StrUtil.isEmpty(generatorVo.getHostname())) {
            message = "主机名不能为空";
        }
        //数据库名不能为空
        if (StrUtil.isEmpty(message) && StrUtil.isEmpty(generatorVo.getDbName())) {
            message = "数据库名不能为空";
        }
        //用户名不能为空
        if (StrUtil.isEmpty(message) && StrUtil.isEmpty(generatorVo.getDbUser())) {
            message = "用户名不能为空";
        }
        //密码不能为空
        if (StrUtil.isEmpty(message) && StrUtil.isEmpty(generatorVo.getDbPwd())) {
            message = "密码不能为空";
        }
        //端口号不能为空
        if (StrUtil.isEmpty(message) && StrUtil.isEmpty(generatorVo.getPort())) {
            message = "端口号不能为空";
        }
        return message;
    }

    /**
     * 校验-生成代码并下载的参数校验
     *
     * @param generatorVo 画面提交参数
     * @return 错误信息
     * @author ares5k
     */
    private String checkDownloadParam(GeneratorVo generatorVo) {
        //共通校验
        String message = checkConnectParam(generatorVo);
        //包名不能为空
        if (StrUtil.isEmpty(message) && StrUtil.isEmpty(generatorVo.getPackageName())) {
            message = "包名不能为空";
        }
        //最少选择一个需要自动生成代码的表
        if (StrUtil.isEmpty(message) && CollUtil.isEmpty(generatorVo.getCheckedTableNameList())) {
            message = "最少选择一个需要自动生成代码的表";
        }
        //最少选择一个需要自动生成代码的文件类型
        if (StrUtil.isEmpty(message) && CollUtil.isEmpty(generatorVo.getTypeList())) {
            message = "最少选择一个需要自动生成代码的文件类型";
        }
        return message;
    }

    /**
     * 自动生成工具-Service层
     */
    @Autowired
    private GeneratorService generatorService;
}
