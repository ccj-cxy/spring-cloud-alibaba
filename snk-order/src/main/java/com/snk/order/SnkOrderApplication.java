package com.snk.order;

import com.snk.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 订单服务系统
 * @Date : 2021/9/19
 */
@SpringBootApplication
@EnableCustomSwagger2
public class SnkOrderApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnkOrderApplication.class);
    }
}
