package com.snk.userprovider.config;

import lombok.Data;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.context.config.annotation.RefreshScope;
import org.springframework.context.annotation.Configuration;

@RefreshScope
@Data
@Configuration
public class DatabaseConfig {
    @Value(value = "${com.snk.email.service.username}")
    private String username;
    @Value("${server.port}")
    private String port;
}