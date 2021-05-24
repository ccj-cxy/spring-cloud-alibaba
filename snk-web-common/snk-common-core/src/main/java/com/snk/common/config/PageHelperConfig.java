package com.snk.common.config;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInterceptor;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.condition.ConditionalOnClass;
import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.context.annotation.PropertySource;

import java.util.Properties;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 分页插件配置
 * @Date : 2021/5/19
 */
@Configuration
@ConfigurationProperties(prefix="pagehelper")
@PropertySource("classpath:pagehelper.properties")
@Data
/**检测项目中有pageHelper依赖才加载，否则不加载*/
@ConditionalOnClass(PageInterceptor.class)
@Slf4j
public class PageHelperConfig {
    private String  helperDialect;
    private String reasonable;
    private String supportMethodsArguments;
    private String pageSizeZero;
    private String params;


    @Bean
    @Primary
    public PageHelper getPageHelper(){
        log.info("检测到项目中含有pageHelper依赖，自动配置pageHelper对象");
        PageHelper pageHelper=new PageHelper();
        Properties properties=new Properties();
        properties.setProperty("helperDialect",helperDialect);
        properties.setProperty("reasonable",reasonable);
        properties.setProperty("supportMethodsArguments",supportMethodsArguments);
        properties.setProperty("params",params);
        pageHelper.setProperties(properties);
        return pageHelper;
    }
}
