/**
 * Copyright  HuaTu
 * 安徽华图信息科技有限公司
 * http://www.myhuatu.com/
 * All rights reserved.
 */
package com.snk.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :Cai.ChangJun
 * @Description :
 * @Date : 2021/1/5
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableCaching
@RestController
public class ApiGatewayApplication {

    public static void main(String[] args) {
        SpringApplication.run(ApiGatewayApplication.class);
    }

    @GetMapping("/")
    public String hello(){
        return "hello";
    }
}
