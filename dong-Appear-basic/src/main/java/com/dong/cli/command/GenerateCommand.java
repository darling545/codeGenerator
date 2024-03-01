package com.dong.cli.command;


import cn.hutool.core.bean.BeanUtil;
import com.dong.generator.MainGenerator;
import com.dong.model.MainTemplateConfig;
import lombok.Data;
import picocli.CommandLine.Option;
import picocli.CommandLine.Command;

import java.util.concurrent.Callable;

/**
 * 接受参数，生成代码
 */
@Command(name = "generate", mixinStandardHelpOptions = true, description = "generate code")
@Data
public class GenerateCommand implements Callable<Integer> {


    @Option(names = {"-l","--loop"},arity = "0..1",description = "isCycle",interactive = true,echo = true)
    private String loop;

    @Option(names = {"-a","--author"},arity = "0..1",description = "author",interactive = true,echo = true)
    private String author;

    @Option(names = {"-o","--outputText"},arity = "0..1",description = "outputText",interactive = true,echo = true)
    private String outputText;


    @Override
    public Integer call() throws Exception {
        MainTemplateConfig mainTemplateConfig = new MainTemplateConfig();
        BeanUtil.copyProperties(this,mainTemplateConfig);
        System.out.println("配置信息:" + mainTemplateConfig);
        MainGenerator.doGenerator(mainTemplateConfig);
        return 0;
    }
}
