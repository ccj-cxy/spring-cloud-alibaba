package com.snk.auth.controller;


import com.snk.auth.pojo.dto.UserLinkRoleDTO;
import com.snk.auth.service.PublicUserRoleService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户角色关联表 前端控制器
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-27
 */
@RestController
@RequestMapping("/user-and-role")
@Api(value = "用户与角色绑定",tags = "控制用户与角色关系等信息")
@Slf4j
public class PublicUserRoleController {

    @Autowired
    private PublicUserRoleService publicUserRoleService;

    @ApiOperation(value = "添加用户关联角色",notes = "批量添加用户关联角色")
    @PostMapping("/adds")
    public void addUserLinkRole(@RequestBody @Validated UserLinkRoleDTO userLinkRoleDTO){
        publicUserRoleService.addUserLinkRole(userLinkRoleDTO);
    }
}

