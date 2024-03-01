package com.dong.maker.generator.file;

import com.dong.maker.model.DataModel;
import freemarker.template.TemplateException;

import java.io.File;
import java.io.IOException;

/**
 * 核心生成器
 */
public class FileGenerator {

    /**
     * 生成完整的动静结合的代码文件
     * @param model
     * @throws TemplateException
     * @throws IOException
     */
    public static void doGenerator(Object model) throws TemplateException, IOException {
        String projectPath = System.getProperty("user.dir");
        // 获取整个项目的根路径
        File parentFile = new File(projectPath).getParentFile();
        String inputPath = new File(parentFile, "dong-Apper-demo-projects/acm-template").getAbsolutePath();
        String outputPath = projectPath;
        // 生成静态文件
        StaticFileGenerator.copyFilesByHutool(inputPath,outputPath);
        // 生成动态文件
        String inputDynamicFilePath = projectPath + File.separator + "src/main/resources/templates/MainTemplate.java.ftl";
        String outputDynamicFilePath = outputPath + File.separator + "acm-template/src/com/dong/acm/MainTemplate.java";
        DynamicFileGenerator.doGenerate(inputDynamicFilePath,outputDynamicFilePath,model);
    }

    public static void main(String[] args) throws TemplateException, IOException {
        DataModel dataModel = new DataModel();
        dataModel.setAuthor("dongajie");
        dataModel.setOutputText("最后结果");
        dataModel.setLoop(false);
        doGenerator(dataModel);
    }
}
