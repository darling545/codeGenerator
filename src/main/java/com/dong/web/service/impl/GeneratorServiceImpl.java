package com.dong.web.service.impl;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import cn.hutool.core.collection.CollUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.dong.web.common.ErrorCode;
import com.dong.web.constant.CommonConstant;
import com.dong.web.exception.BusinessException;
import com.dong.web.model.dto.generator.GeneratorQueryRequest;
import com.dong.web.model.entity.Generator;
import com.dong.web.model.entity.User;
import com.dong.web.model.vo.GeneratorVO;
import com.dong.web.model.vo.UserVO;
import com.dong.web.service.GeneratorService;
import com.dong.web.mapper.GeneratorMapper;
import com.dong.web.service.UserService;
import com.dong.web.utils.SqlUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.apache.poi.util.StringUtil;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;

/**
* @author Administrator
* @description 针对表【generator(代码生成器)】的数据库操作Service实现
* @createDate 2024-02-26 10:11:32
*/
@Service
public class GeneratorServiceImpl extends ServiceImpl<GeneratorMapper, Generator>
    implements GeneratorService{

    @Resource
    private UserService userService;


    @Override
    public void validGenerator(Generator generator, boolean flag) {
        if (generator == null){
            throw new BusinessException(ErrorCode.PARAMS_ERROR,"请求参数为空，请重新填写");
        }
        String name = generator.getName();
        String description = generator.getDescription();

        if (flag){
            if (StringUtils.isNotBlank(name) && name.length() > 80){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"名称过长");
            }

            if (StringUtils.isNotBlank(description) && description.length() > 256){
                throw new BusinessException(ErrorCode.PARAMS_ERROR,"描述过长");
            }
        }
    }

    @Override
    public GeneratorVO getGeneratorVO(Generator generator, HttpServletRequest request) {
        GeneratorVO generatorVO = GeneratorVO.objToVo(generator);
        long generatorId = generator.getId();
        // 关联用户信息
        Long userId = generator.getUserId();
        User user = null;
        if (userId != null && userId > 0) {
            user = userService.getById(userId);
        }
        UserVO userVO = userService.getUserVO(user);
        generatorVO.setUser(userVO);
        return generatorVO;
    }

    @Override
    public QueryWrapper<Generator> getQueryWrapper(GeneratorQueryRequest generatorQueryRequest) {
        QueryWrapper<Generator> queryWrapper = new QueryWrapper<>();
        if (generatorQueryRequest == null){
            return queryWrapper;
        }
        Long id = generatorQueryRequest.getId();
        Long notId = generatorQueryRequest.getNotId();
        String searchText = generatorQueryRequest.getSearchText();
        List<String> tags = generatorQueryRequest.getTags();
        Long userId = generatorQueryRequest.getUserId();
        String name = generatorQueryRequest.getName();
        String description = generatorQueryRequest.getDescription();
        String basePackage = generatorQueryRequest.getBasePackage();
        String version = generatorQueryRequest.getVersion();
        String author = generatorQueryRequest.getAuthor();
        String distPath = generatorQueryRequest.getDistPath();
        Integer status = generatorQueryRequest.getStatus();
        String sortField = generatorQueryRequest.getSortField();
        String sortOrder = generatorQueryRequest.getSortOrder();
        // 拼接查询条件
        if (StringUtils.isNotBlank(searchText)) {
            queryWrapper.and(qw -> qw.like("name", searchText).or().like("description", searchText));
        }
        queryWrapper.like(StringUtils.isNotBlank(name), "name", name);
        queryWrapper.like(StringUtils.isNotBlank(description), "description", description);
        if (CollUtil.isNotEmpty(tags)) {
            for (String tag : tags) {
                queryWrapper.like("tags", "\"" + tag + "\"");
            }
        }
        queryWrapper.ne(ObjectUtils.isNotEmpty(notId), "id", notId);
        queryWrapper.eq(ObjectUtils.isNotEmpty(id), "id", id);
        queryWrapper.eq(ObjectUtils.isNotEmpty(userId), "userId", userId);
        queryWrapper.eq(StringUtils.isNotBlank(basePackage), "basePackage", basePackage);
        queryWrapper.eq(StringUtils.isNotBlank(version), "version", version);
        queryWrapper.eq(StringUtils.isNotBlank(author), "author", author);
        queryWrapper.eq(StringUtils.isNotBlank(distPath), "distPath", distPath);
        queryWrapper.eq(ObjectUtils.isNotEmpty(status), "status", status);
        queryWrapper.orderBy(SqlUtils.validSortField(sortField), sortOrder.equals(CommonConstant.SORT_ORDER_ASC),
                sortField);

        return queryWrapper;
    }

    @Override
    public Page<GeneratorVO> getGeneratorVOPage(Page<Generator> generatorPage, HttpServletRequest request) {
        List<Generator> generatorList = generatorPage.getRecords();
        Page<GeneratorVO> generatorVOPage = new Page<>(generatorPage.getCurrent(), generatorPage.getSize(),
                generatorPage.getTotal());
        if (CollUtil.isEmpty(generatorList)){
            return generatorVOPage;
        }
        // 1. 关联查询用户信息
        Set<Long> userIdSet = generatorList.stream().map(Generator::getUserId).collect(Collectors.toSet());
        Map<Long, List<User>> userIdUserListMap = userService.listByIds(userIdSet).stream()
                .collect(Collectors.groupingBy(User::getId));
        // 填充信息
        List<GeneratorVO> generatorVOList = generatorList.stream().map(generator -> {
            GeneratorVO generatorVO = GeneratorVO.objToVo(generator);
            Long userId = generator.getUserId();
            User user = null;
            if (userIdUserListMap.containsKey(userId)) {
                user = userIdUserListMap.get(userId).get(0);
            }
            generatorVO.setUser(userService.getUserVO(user));
            return generatorVO;
        }).collect(Collectors.toList());
        generatorVOPage.setRecords(generatorVOList);
        return generatorVOPage;
    }
}




