package com.snk.email.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.sun.mail.util.MailSSLSocketFactory;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import javax.validation.constraints.NotEmpty;
import java.security.GeneralSecurityException;
import java.util.Properties;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 邮件发送人信息
 * @Date : 2021/7/4
 */
@Data
@ToString
@EqualsAndHashCode
@ApiModel("邮件发送人信息")
public class EmailFromDTO {
    @ApiModelProperty("发件人用户名")
    @NotEmpty(message = "发件人用户名不能为空")
    private String username;

    @ApiModelProperty("发件人邮箱密码")
    @NotEmpty(message = "发件人邮箱密码不能为空")
    private String password;

    @ApiModelProperty("邮件服务器登录信息")
    @NotEmpty(message = "邮件服务器登录信息不能为空")
    private String host;

    /**
     * 其他相关邮件配置
     */
    @JsonIgnore
    private Properties properties = new Properties();

    public Properties getProperties() {
        return properties;
    }

    /**
     * 配置邮件信息，固定配置
     * @author Cai.ChangJun
     * @return:
     * @version 1.0.0
     * @Date 2021/7/4 16:44
     */
    public void setProperties(Properties properties) throws GeneralSecurityException {
        properties.setProperty("mail.smtp.auth", "true");
        // 加密端口(ssl)  可通过 https://qiye.163.com/help/client-profile.html 进行查询
        properties.setProperty("mail.smtp.timeout", "25000");
        properties.setProperty("mail.smtp.writetimeout", "30000");
        // SSL加密
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        // 设置信任所有的主机
        sf.setTrustAllHosts(true);
        properties.put("mail.smtp.ssl.enable", "true");
        properties.put("mail.smtp.ssl.socketFactory", sf);
        this.properties = properties;
    }
}
