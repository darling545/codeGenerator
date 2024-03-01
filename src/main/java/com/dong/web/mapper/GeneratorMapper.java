package com.dong.web.mapper;

import com.dong.web.model.entity.Generator;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import org.apache.ibatis.annotations.Select;

import java.util.List;

/**
* @author Administrator
* @description 针对表【generator(代码生成器)】的数据库操作Mapper
* @createDate 2024-02-26 10:11:32
* @Entity com.dong.web.model.entity.Generator
*/
public interface GeneratorMapper extends BaseMapper<Generator> {

    @Select("SELECT id, distPath FROM generator WHERE isDelete = 1")
    List<Generator> listDeletedGenerator();

}




