package com.ares5k.utils;

import cn.hutool.core.io.FileUtil;
import com.ares5k.template.TemplateData;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.FileWriter;
import java.io.IOException;

/**
 * 自动生成工具-Freemarker Template工具类
 *
 * @author ares5k
 * @since 2020-09-16
 * qq: 16891544
 * email: 16891544@qq.com
 */
public class FreemarkerUtil {
    /**
     * 解析指定的Freemarker模板, 并在磁盘生成解析后的文件
     *
     * @param templateData Freemarker Template中要替换的数据
     * @param templateName Freemarker模板名
     * @param path         解析后生成文件的磁盘保存路径
     * @throws IOException       IO异常
     * @throws TemplateException Freemarker Template解析异常
     * @author ares5k
     */
    public static void processTemplate(TemplateData templateData, String templateName, String path) throws IOException, TemplateException {
        //创建目录及文件
        FileWriter fileWriter = new FileWriter(FileUtil.touch(path));
        //Freemarker配置
        Configuration configuration = new Configuration(Configuration.VERSION_2_3_28);
        configuration.setClassForTemplateLoading(FreemarkerUtil.class, "/freemarker");
        configuration.setDefaultEncoding("utf-8");
        //获取指定的Freemarker模板
        Template template = configuration.getTemplate(templateName);
        //解析模板并生成文件到指定磁盘路径
        template.process(templateData, fileWriter);
        //关闭IO
        fileWriter.close();
    }
}
