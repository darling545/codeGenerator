package com.dong.cli.command;



import cn.hutool.core.io.FileUtil;
import picocli.CommandLine.Command;

import java.io.File;
import java.util.List;

@Command(name = "list", description = "List all the available commands.",mixinStandardHelpOptions = true)
public class ListCommand implements Runnable{

    @Override
    public void run() {
        String projectPath = System.getProperty("user.dir");
        File parentPath = new File(projectPath).getParentFile();

        String inputPath = new File(parentPath,"dong-Apper-demo-projects/acm-template").getAbsolutePath();
        List<File> files = FileUtil.loopFiles(inputPath);
        for (File file : files){
            System.out.println(file);
        }
    }
}
