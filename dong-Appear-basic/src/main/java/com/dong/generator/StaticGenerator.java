package com.dong.generator;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.util.ArrayUtil;

import java.io.File;
import java.io.IOException;
import java.nio.file.Path;
import java.nio.file.StandardCopyOption;

public class StaticGenerator {


    /**
     * copy文件（使用hutool的FileUtil，将输入的目录复制到输出目录当中）
     * @param inputPath
     * @param outputPath
     */
    public static void copyFilesByHutool(String inputPath, String outputPath){
        FileUtil.copy(inputPath, outputPath, false);
    }

    public static void copyFilesByRecursive(String inputPath, String outputPath){
        File inputFile = new File(inputPath);
        File outputFile = new File(outputPath);
        try {
            copyFileByRecursive(inputFile,outputFile);
        } catch (IOException e) {
            System.out.println("文件复制失败");
            throw new RuntimeException(e);
        }
    }


    public static void main(String[] args) {
        /**
         * 整个目录进行移动复制，简单，但是并不是灵活的
         * @param args
         */
        String projectPath = System.getProperty("user.dir");
        File parentPath = new File(projectPath).getParentFile();
        String inputPath = new File(parentPath,"dong-Apper-demo-projects/acm-template").getAbsolutePath();
        String outputPath = projectPath;
        copyFilesByHutool(inputPath, outputPath);
        System.out.println("静态文件复制完成");

    }

    public static void copyFileByRecursive(File inputFile, File outputFile) throws IOException {
        if (inputFile.isDirectory()){
            System.out.println(inputFile.getName());
            File destOutputFile = new File(outputFile, inputFile.getName());
            if (!destOutputFile.exists()){
                destOutputFile.mkdirs();
            }
            File[] inputFiles = inputFile.listFiles();
            if (ArrayUtil.isEmpty(inputFiles)){
                return;
            }
            for (File file : inputFiles){
                copyFileByRecursive(file,destOutputFile);
            }
        }else {
            Path destPath = outputFile.toPath().resolve(inputFile.getName());
            FileUtil.copy(inputFile.toPath(),destPath, StandardCopyOption.REPLACE_EXISTING);
        }
    }
}
