package com.dong.maker.generator;

import java.io.*;

public class JarGenerator {


    public static void doGenerate(String projectDir) throws IOException, InterruptedException {
        // 根据操作系统来选取执行的编译命令
        String winMavenCommand = "mvn.cmd clean package -DskipTests=true";
        String otherMavenCommand = "mvn clean package -DskipTests=true";
        String mavenCommand = winMavenCommand;


        ProcessBuilder processBuilder = new ProcessBuilder(mavenCommand.split(" "));
        processBuilder.directory(new File(projectDir));

        Process process = processBuilder.start();

        InputStream inputStream = process.getInputStream();
        BufferedReader reader = new BufferedReader(new InputStreamReader(inputStream));
        String line;
        while ((line = reader.readLine()) != null) {
            System.out.println(line);
        }

        int exitCode = process.waitFor();
        System.out.println("Maven build exited with code: " + exitCode);

    }

    public static void main(String[] args) throws IOException, InterruptedException {
        doGenerate("D:\\codeAppear\\dong-Appear\\dong-Generator-maker\\generated\\acm-template-pro-generator");
    }
}
