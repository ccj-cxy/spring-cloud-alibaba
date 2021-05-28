package com.snk.auth.controller;


import com.snk.auth.pojo.domain.PublicResource;
import com.snk.auth.pojo.dto.RoleLinkResourceDTO;
import com.snk.auth.service.PublicRoleResourceService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * <p>
 * 角色资源关联表 前端控制器
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-28
 */
@RestController
@RequestMapping("/public-role-resource")
@Api(value = "角色与资源绑定关系", tags = "控制角色资源关系等信息")
public class PublicRoleResourceController {

    @Autowired
    private PublicRoleResourceService publicRoleResourceService;

    @PostMapping("/getAllResource")
    @ApiOperation(value = "获取所有资源",notes = "根据roleId集合获取所有角色的资源")
    public Set<PublicResource> getAllResource(@RequestBody List<Integer> roleIds) {
        return publicRoleResourceService.getAllResource(roleIds.stream().collect(Collectors.toSet()));
    }

    @PostMapping("/addRoleResource")
    @ApiOperation(value = "添加角色绑定资源",notes = "单个role绑定多个资源")
    public void addRoleResource(@RequestBody RoleLinkResourceDTO roleLinkResource) {
        publicRoleResourceService.addRoleResource(roleLinkResource);
    }

}

