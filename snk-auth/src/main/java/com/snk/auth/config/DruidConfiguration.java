package com.snk.auth.config;
import com.alibaba.druid.support.http.StatViewServlet;
import org.springframework.boot.web.servlet.ServletRegistrationBean;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;


/**
 * @ClassName cn.saytime.com.snk.email.config.DruidConfiguration
 * @Description
 */
@Configuration
public class DruidConfiguration {

        /**
         * 配置监控服务器
         * @return 返回监控注册的servlet对象
         * @author SimpleWu
         */
        @Bean
        public ServletRegistrationBean statViewServlet() {
            ServletRegistrationBean servletRegistrationBean = new ServletRegistrationBean(new StatViewServlet(), "/druid/*");
            // 添加IP白名单
            servletRegistrationBean.addInitParameter("allow", "127.0.0.1");
            // 添加IP黑名单，当白名单和黑名单重复时，黑名单优先级更高
            servletRegistrationBean.addInitParameter("deny", "");
            // 添加控制台管理用户
            servletRegistrationBean.addInitParameter("loginUsername", "admin");
            servletRegistrationBean.addInitParameter("loginPassword", "admin");
            // 是否能够重置数据
            servletRegistrationBean.addInitParameter("resetEnable", "false");
            return servletRegistrationBean;
        }


}