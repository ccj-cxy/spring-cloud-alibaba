package com.snk.amqp;

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
 * @Description : 启动类
 * @Date : 2021/5/7
 */
@SpringBootApplication
@EnableDiscoveryClient
@EnableFeignClients
@EnableCustomSwagger2
public class AmqpApplication {

    public static void main(String[] args) {
        SpringApplication.run(AmqpApplication.class);
    }

    @Bean
    @LoadBalanced
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
