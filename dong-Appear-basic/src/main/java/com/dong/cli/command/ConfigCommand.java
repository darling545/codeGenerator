package com.dong.cli.command;


import cn.hutool.core.util.ReflectUtil;
import com.dong.model.MainTemplateConfig;
import picocli.CommandLine.Command;

import java.lang.reflect.Field;

@Command(name = "config", mixinStandardHelpOptions = true, description = "check the config info")
public class ConfigCommand implements Runnable{
    @Override
    public void run() {
        System.out.println("check  the config info");
        Field[] fields = ReflectUtil.getFields(MainTemplateConfig.class);
        for (Field field : fields){
            System.out.println("name" + field.getName());
            System.out.println("type" + field.getType());
            System.out.println("----");
        }
    }
}
