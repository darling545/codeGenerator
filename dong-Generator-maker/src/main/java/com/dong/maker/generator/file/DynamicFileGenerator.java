package com.dong.maker.generator.file;

import cn.hutool.core.io.FileUtil;
import com.dong.maker.model.DataModel;
import freemarker.template.Configuration;
import freemarker.template.Template;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;

/**
 * 动态生成文件
 */
public class DynamicFileGenerator {


    /**
     *
     * 生成文件
     * @param inputPath
     * @param outputPath
     * @param model
     * @throws IOException
     * @throws TemplateException
     */
    public static void doGenerate(String inputPath,String outputPath,Object model) throws IOException,
            TemplateException {
        // 配置信息，制定使用的版本
        Configuration myCfg = new Configuration(Configuration.VERSION_2_3_32);
        // 制定模板的所在目录
        File templateDir = new File(inputPath).getParentFile();
        myCfg.setDirectoryForTemplateLoading(templateDir);
        // 制定模板文件所示用的字符集
        myCfg.setDefaultEncoding("UTF-8");

        // 获取示例的模板文件
        String templateName = new File(inputPath).getName();
        Template template = myCfg.getTemplate(templateName);

//        // 创建数据模型
//        DataModel dataModel = new DataModel();
//        dataModel.setAuthor("dong");
//        dataModel.setOutputText("最后结果");
//        dataModel.setLoop(false);

        // 如果文件不存在，则创建
        if (!FileUtil.exist(outputPath)){
            FileUtil.touch(outputPath);
        }

        // 生成
        Writer out = new FileWriter(outputPath);
        template.process(model, out);

        // 关闭数据流
        out.close();
    }

    public static void main(String[] args) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");
        String inputPath = projectPath + File.separator + "src/main/resources/templates";
        String outputPath = projectPath + File.separator + "MainTemplate.java";
        DataModel dataModel = new DataModel();
        dataModel.setAuthor("dong");
        dataModel.setOutputText("最后结果");
        dataModel.setLoop(false);
        doGenerate(inputPath,outputPath, dataModel);

    }
}
