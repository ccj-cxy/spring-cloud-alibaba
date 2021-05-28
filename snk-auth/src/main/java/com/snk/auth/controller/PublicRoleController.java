package com.snk.auth.controller;


import com.snk.auth.pojo.domain.PublicRole;
import com.snk.auth.service.PublicRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 角色表 前端控制器
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-27
 */
@RestController
@RequestMapping("/role")
@Api(value = "角色管理",tags = "控制角色新增等信息")
@Slf4j
public class PublicRoleController {

    @Autowired
    private PublicRoleService publicRoleService;

    @ApiOperation(value = "添加角色",notes = "添加角色相关信息")
    @PostMapping("/addRole")
    public PublicRole addRole(@RequestBody PublicRole role) {
        publicRoleService.save(role);
        return role;
    }
}

