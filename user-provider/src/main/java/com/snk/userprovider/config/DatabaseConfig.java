package com.snk.userprovider.config;

import com.alibaba.nacos.api.config.annotation.NacosValue;
import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Data
@Configuration
public class DatabaseConfig {
    @NacosValue(value = "${username}", autoRefreshed = true)
    private String username;
    @Value("${server.port}")
    private String port;
}