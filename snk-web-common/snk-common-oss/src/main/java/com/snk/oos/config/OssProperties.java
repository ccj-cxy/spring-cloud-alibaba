package com.snk.oos.config;

import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 阿里云oos配置类
 * @Date : 2021/7/16
 */
@Configuration
@ConfigurationProperties("ali-oss")
@PropertySource("classpath:oss.properties")
@Data
public class OssProperties {

    private String accessKeyId;

    private String accessKeySecret;

    private String bucketName;

    private String dir;

    private String endpoint;

    private String bucketUrl;

}
