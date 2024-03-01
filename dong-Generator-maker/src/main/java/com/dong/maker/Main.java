package com.dong.maker;

import com.dong.maker.cli.CommandExecutor;
import com.dong.maker.generator.main.GenerateTemplate;
import com.dong.maker.generator.main.MainGenerator;
import freemarker.template.TemplateException;

import java.io.IOException;

public class Main {
    public static void main(String[] args) throws TemplateException, IOException, InterruptedException {
        MainGenerator mainGenerator = new MainGenerator();
        mainGenerator.doGenerate();
    }
}
