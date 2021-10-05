package com.snk.quartz;

import com.snk.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import org.springframework.web.client.RestTemplate;

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
public class QuartzApplication {
    public static void main(String[] args) {
        SpringApplication.run(QuartzApplication.class);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
