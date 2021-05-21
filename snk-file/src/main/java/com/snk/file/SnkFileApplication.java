package com.snk.file;

import com.snk.swagger.annotation.EnableCustomSwagger2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 文件管理服务
 * @Date : 2021/5/21
 */
@SpringBootApplication
@EnableCustomSwagger2
public class SnkFileApplication {

    public static void main(String[] args) {
        SpringApplication.run(SnkFileApplication.class, args);
    }
}
