package com.snk.auth;

import com.snk.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 用户管理模块服务
 * @Date : 2021/5/26
 */
@SpringBootApplication
@EnableCustomSwagger2
public class AuthApplication {

    public static void main(String[] args) {
        SpringApplication.run(AuthApplication.class);
    }
}
