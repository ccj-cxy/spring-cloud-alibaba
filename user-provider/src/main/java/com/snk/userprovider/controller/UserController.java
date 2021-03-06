package com.snk.userprovider.controller;

import com.snk.userprovider.config.DatabaseConfig;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * openfeign调用测试
 * @author Cai.ChangJun
 * @version 1.0.0
 * @Date 2021/5/14 23:22
 */
@RestController
@RequestMapping("user")
public class UserController {
    @Autowired
    private DatabaseConfig databaseConfig;
    @Value("${server.port}")
    private String appName;
    @RequestMapping("config")
    public String config() {
        System.out.println(appName+"执行了");
        return databaseConfig.getUsername()+":"+databaseConfig.getPort();
    }
}