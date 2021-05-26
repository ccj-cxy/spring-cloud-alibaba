package com.snk.jwt.config;

import com.snk.jwt.utils.JWTUtil;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : jwt相关配置信息
 * @Date : 2021/5/26
 */
@Configuration
@ConfigurationProperties("jwt")
@PropertySource("classpath:jwt.properties")
@Data
public class JwtConfig {
    /**默认过期时间*/
    private Long ttl;
    /**密钥明文*/
    private String key;
    /**密钥生成算法*/
    private String algorithm;

    @Bean
    public JWTUtil jwtUtil() {
        return new JWTUtil(ttl,key,algorithm);
    }
}
