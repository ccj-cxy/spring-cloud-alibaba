package com.snk.auth.controller;


import com.snk.auth.pojo.domain.PublicUser;
import com.snk.auth.pojo.dto.UserDTO;
import com.snk.auth.service.PublicUserService;
import com.snk.common.webRetrun.Response;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;

import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

/**
 * <p>
 * 用户表 前端控制器
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-26
 */
@RestController
@RequestMapping("/user")
@Api(value = "用户管理",tags = "控制用户登录注册等信息")
@Slf4j
public class PublicUserController {
    @Autowired
    private PublicUserService publicUserService;

    @PostMapping("/registered")
    @ApiOperation(value = "注册",tags = "在系统中注册信息并发送邮件")
    public Response<String> registered(@RequestBody PublicUser publicUser) {
        log.info("注册用户信息{}",publicUser);
        publicUserService.save(publicUser);
        return new Response<>("注册成功");
    }


    @GetMapping("/login")
    @ApiOperation(value = "登录",tags = "用户登录系统，暂时get调用，并且显示传参后面修改为post隐式传参")
    public Response<UserDTO> login(@RequestParam("username") String username,@RequestParam("password") String password) {
        log.info("用户登录：{}",username);
        UserDTO login = publicUserService.login(username, password);
        return new Response<>(login);
    }
}

