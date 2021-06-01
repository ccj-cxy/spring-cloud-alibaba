package com.snk.auth.controller;


import com.snk.auth.pojo.domain.PublicUser;
import com.snk.auth.pojo.dto.UserDTO;
import com.snk.auth.pojo.param.LoginParam;
import com.snk.auth.service.LoginService;
import com.snk.auth.service.PublicUserService;
import com.snk.auth.service.impl.LoginStrategyFactory;
import com.snk.common.webRetrun.Response;
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
    @Autowired
    private LoginStrategyFactory loginStrategyFactory;

    @PostMapping("/registered")
    @ApiOperation(value = "注册",notes = "在系统中注册信息并发送邮件")
    public Response<String> registered(@RequestBody PublicUser publicUser) {
        log.info("注册用户信息{}",publicUser);
        publicUserService.save(publicUser);
        return new Response<>("注册成功");
    }


    @PostMapping("/login")
    @ApiOperation(value = "登录",notes = "用户登录系统，根据登录类型区分不同的登录方式")
    public Response<UserDTO> login(@RequestBody @Validated LoginParam loginParam) {
        log.info("用户登录：{}",loginParam.getUsername());
        LoginService strategy = loginStrategyFactory.getStrategy(loginParam.getLoginType());
        UserDTO login = strategy.login(loginParam.getUsername(), loginParam.getPassword());
        return new Response<>(login);
    }
}

