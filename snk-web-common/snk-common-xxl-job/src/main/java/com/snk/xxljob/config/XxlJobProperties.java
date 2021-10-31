package com.snk.xxljob.config;
import lombok.Data;
import org.springframework.boot.context.properties.ConfigurationProperties;

/**
 * xxl-job配置属性
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description :
 * @Date : 2021/10/26
 */
@Data
@ConfigurationProperties(prefix = "xxl.job")
public class XxlJobProperties {

    private AdminProperties admin = new AdminProperties();

    private String accessToken;

    private ExecutorProperties executor = new ExecutorProperties();

    @Data
    public static class ExecutorProperties  {
        private String  appname;
        private String address;
        private String ip;
        private int port;
        private String logPath;
        private int logRetentionDays;
    }
    @Data
    public static class AdminProperties  {
        private String  addresses;
    }
}