package com.snk.websocket;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;

/**
 * 排除autoconfiguration中的DataSourceAutoConfiguration 当项目中不需要连接数据源的话
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description :
 * @Date : 2021/5/25
 */
@SpringBootApplication(exclude= {DataSourceAutoConfiguration.class})
public class WebSocketApplication {

    public static void main(String[] args) {
        SpringApplication.run(WebSocketApplication.class);
    }
}
