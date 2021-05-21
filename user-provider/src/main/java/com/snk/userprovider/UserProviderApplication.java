package com.snk.userprovider;

import com.snk.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.openfeign.EnableFeignClients;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description :
 * @Date : 2021/4/29
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCustomSwagger2
public class UserProviderApplication {
    public static void main(String[] args) {
        SpringApplication.run(UserProviderApplication.class);
    }
}
