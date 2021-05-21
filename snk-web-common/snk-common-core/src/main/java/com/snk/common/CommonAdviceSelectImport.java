package com.snk.common;

import org.springframework.boot.autoconfigure.condition.ConditionalOnMissingBean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.context.ConfigurableWebApplicationContext;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 扫描jar包
 * @Date : 2021/5/19
 */
@Configuration
@ConditionalOnMissingBean({ConfigurableWebApplicationContext.class})
@ComponentScan({"com.snk.common"})
public class CommonAdviceSelectImport {
    public CommonAdviceSelectImport(){}
}
