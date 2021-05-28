package com.snk.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snk.auth.pojo.domain.PublicResource;
import com.snk.auth.dao.PublicResourceMapper;
import com.snk.auth.service.PublicResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 资源表 服务实现类
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-28
 */
@Service
public class PublicResourceServiceImpl extends ServiceImpl<PublicResourceMapper, PublicResource> implements PublicResourceService {

    @Autowired
    private PublicResourceMapper publicResourceMapper;

    @Override
    public Set<PublicResource> getResources(Set<Integer> resourceIds) {
        QueryWrapper<PublicResource> publicResourceQueryWrapper = new QueryWrapper<>();
        publicResourceQueryWrapper.lambda().in(PublicResource::getId,resourceIds);
        List<PublicResource> publicResources = publicResourceMapper.selectList(publicResourceQueryWrapper);
        return publicResources.stream().collect(Collectors.toSet());
    }
}
