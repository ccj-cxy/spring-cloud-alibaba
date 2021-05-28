package com.snk.auth.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.snk.auth.pojo.domain.PublicResource;
import com.snk.auth.pojo.domain.PublicRoleResource;
import com.snk.auth.dao.PublicRoleResourceMapper;
import com.snk.auth.pojo.dto.RoleLinkResourceDTO;
import com.snk.auth.service.PublicResourceService;
import com.snk.auth.service.PublicRoleResourceService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.snk.common.exception.BusinessException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色资源关联表 服务实现类
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-28
 */
@Service
public class PublicRoleResourceServiceImpl extends ServiceImpl<PublicRoleResourceMapper, PublicRoleResource> implements PublicRoleResourceService {

    @Autowired
    private PublicRoleResourceMapper publicRoleResourceMapper;
    @Autowired
    private PublicResourceService publicResourceService;

    @Override
    public Set<PublicResource> getAllResource(Set<Integer> roleIds) {
        //查询出所有资源id
        QueryWrapper<PublicRoleResource> queryWrapper = new QueryWrapper<>();
        queryWrapper.lambda().in(PublicRoleResource::getRoleId,roleIds);
        List<PublicRoleResource> publicRoleResources = publicRoleResourceMapper.selectList(queryWrapper);
        Set<Integer> resourceIds = publicRoleResources.stream().map(PublicRoleResource::getResourceId).collect(Collectors.toSet());
        if (resourceIds.size()<=0) {
            throw new BusinessException("未查询到任何资源，请联系管理员添加资源");
        }
        //根据资源id检索出所有资源信息
        return publicResourceService.getResources(resourceIds);
    }

    @Override
    @Transactional(rollbackFor = Exception.class)
    public void addRoleResource(RoleLinkResourceDTO roleLinkResource) {
        //先清空当前角色下所有资源
        QueryWrapper<PublicRoleResource> roleResourceQueryWrapper = new QueryWrapper<>();
        roleResourceQueryWrapper.lambda().eq(PublicRoleResource::getRoleId,roleLinkResource.getRoleId());
        publicRoleResourceMapper.delete(roleResourceQueryWrapper);
        //重新绑定当前角色的资源
        List<PublicRoleResource> roleResources = new ArrayList<>();
        roleLinkResource.getResourceIds().forEach(resourceId->{
            PublicRoleResource publicRoleResource = new PublicRoleResource();
            publicRoleResource.setRoleId(roleLinkResource.getRoleId());
            publicRoleResource.setResourceId(resourceId);
            roleResources.add(publicRoleResource);
        });
        this.saveBatch(roleResources);
    }

}
