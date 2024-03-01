package com.dong.maker.meta;
import cn.hutool.core.collection.CollectionUtil;
import cn.hutool.core.io.FileUtil;


import cn.hutool.core.date.DateUtil;
import cn.hutool.core.util.StrUtil;
import com.dong.maker.meta.enums.FileGenerateTypeEnum;
import com.dong.maker.meta.enums.FileTypeEnum;
import com.dong.maker.meta.enums.ModelTypeEnum;

import java.nio.file.Paths;
import java.util.List;
import java.util.stream.Collectors;

/**
 * 元信息校验
 */
public class MetaValidator {

    public static void doValidAndFill(Meta meta) {
        validAndFillMetaRoot(meta);
        validAndFillFileConfig(meta);
        validAndFillModelConfig(meta);
    }

    private static void validAndFillModelConfig(Meta meta) {
        Meta.ModelConfig modelConfig = meta.getModelConfig();
        if (modelConfig == null){
            return;
        }

        List<Meta.ModelConfig.ModelInfo> modelInfoList = modelConfig.getModels();
        if (!CollectionUtil.isNotEmpty(modelInfoList)) {
            return;
        }

        for (Meta.ModelConfig.ModelInfo modelInfo : modelInfoList){

            String groupKey = modelInfo.getGroupKey();
            if (StrUtil.isNotEmpty(groupKey)){
                if (StrUtil.isNotEmpty(groupKey)){
                    List<Meta.ModelConfig.ModelInfo> subModelInfoList = modelInfo.getModels();
                    String allArgsStr = modelInfo.getModels().stream().map(subModelInfo -> String.format("\"--%s\"",
                            subModelInfo.getFieldName())).collect(Collectors.joining(","));
                    modelInfo.setAllArgsStr(allArgsStr);
                }
                continue;
            }

            String fieldName = modelInfo.getFieldName();
            if (StrUtil.isBlank(fieldName)){
                throw new MetaException("未填写 fieldName");
            }
            String modelInfoType = modelInfo.getType();
            if (StrUtil.isEmpty(modelInfoType)){
                modelInfo.setType(ModelTypeEnum.STRING.getValue());
            }
        }
    }

    private static void validAndFillFileConfig(Meta meta) {
        Meta.FileConfig fileConfig = meta.getFileConfig();
        if (fileConfig == null){
            return;
        }
        String sourceRootPath = fileConfig.getSourceRootPath();
        if (StrUtil.isBlank(sourceRootPath)){
            throw new MetaException("未填写 sourceRootPath");
        }

        String inputRootPath = fileConfig.getInputRootPath();
        String defaultInputRootPath =
                ".source/" + FileUtil.getLastPathEle(Paths.get(sourceRootPath)).getFileName().toString();
        if (StrUtil.isEmpty(inputRootPath)){
            fileConfig.setInputRootPath(defaultInputRootPath);
        }

        String outputRootPath = fileConfig.getOutputRootPath();
        String outputDefaultRootPath = "generated";
        if (StrUtil.isEmpty(outputRootPath)){
            fileConfig.setOutputRootPath(outputDefaultRootPath);
        }

        String fileConfigType = fileConfig.getType();
        String defaultType = FileTypeEnum.DIR.getValue();
        if (StrUtil.isEmpty(fileConfigType)){
            fileConfig.setType(defaultType);
        }

        List<Meta.FileConfig.FileInfo> fileInfoList = fileConfig.getFiles();
        if (!CollectionUtil.isNotEmpty(fileInfoList)){
            return;
        }
        for (Meta.FileConfig.FileInfo fileInfo : fileInfoList){

            String type = fileInfo.getType();
            if (FileTypeEnum.GROUP.getValue().equals(type)){
                continue;
            }

            String inputPath = fileInfo.getInputPath();
            if (StrUtil.isBlank(inputPath)){
                throw new MetaException("未填写 inputPath");
            }

            // outputPath路径 默认和inputPath一致
            String outputPath = fileInfo.getOutputPath();
            if (StrUtil.isEmpty(outputPath)){
                fileInfo.setOutputPath(inputPath);
            }
            if (StrUtil.isBlank(type)){
                if (StrUtil.isBlank(FileUtil.getSuffix(inputPath))){
                    fileInfo.setType(FileTypeEnum.DIR.getValue());
                }else {
                    fileInfo.setType(FileTypeEnum.FILE.getValue());
                }
            }

            String generateType = fileInfo.getGenerateType();
            if (StrUtil.isBlank(generateType)){
                // 动态模板
                if (inputPath.endsWith(".ftl")){
                    fileInfo.setGenerateType(FileGenerateTypeEnum.DYNAMIC.getValue());
                }else {
                    fileInfo.setGenerateType(FileGenerateTypeEnum.STATIC.getValue());
                }
            }
        }
    }

    private static void validAndFillMetaRoot(Meta meta) {
        // 填充并且校验默认值
        String name = StrUtil.blankToDefault(meta.getName(),"my-generator");
        String description = StrUtil.emptyToDefault(meta.getDescription(),"我的模板代码生成器");
        String author = StrUtil.emptyToDefault(meta.getAuthor(),"dong");
        String basePackage = StrUtil.blankToDefault(meta.getBasePackage(),"com.dong");
        String version = StrUtil.emptyToDefault(meta.getVersion(),"1.0");
        String createTime = StrUtil.emptyToDefault(meta.getCreateTime(), DateUtil.now());
        meta.setName(name);
        meta.setDescription(description);
        meta.setBasePackage(basePackage);
        meta.setVersion(version);
        meta.setAuthor(author);
        meta.setCreateTime(createTime);
    }
}
