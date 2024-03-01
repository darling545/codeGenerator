package com.dong.model;

import lombok.Data;

/**
 * 动态模板配制
 */
@Data
public class MainTemplateConfig {

    /**
     * 是否循环
     */
    private boolean loop;

    /**
     * 作者注释
     */
    private String author = "dong";

    /**
     * 输出信息
     */
    private String outputText = "结果 = ";
}
