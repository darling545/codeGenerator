package com.dong.web.service;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.dong.web.model.dto.generator.GeneratorQueryRequest;
import com.dong.web.model.entity.Generator;
import com.baomidou.mybatisplus.extension.service.IService;
import com.dong.web.model.vo.GeneratorVO;

import javax.servlet.http.HttpServletRequest;

/**
* @author Administrator
* @description 针对表【generator(代码生成器)】的数据库操作Service
* @createDate 2024-02-26 10:11:32
*/
public interface GeneratorService extends IService<Generator> {


    /**
     * 校验
     * @param generator
     * @param b
     */
    void validGenerator(Generator generator, boolean b);


    GeneratorVO getGeneratorVO(Generator generator, HttpServletRequest request);

    QueryWrapper<Generator> getQueryWrapper(GeneratorQueryRequest generatorQueryRequest);

    Page<GeneratorVO> getGeneratorVOPage(Page<Generator> generatorPage, HttpServletRequest request);
}
