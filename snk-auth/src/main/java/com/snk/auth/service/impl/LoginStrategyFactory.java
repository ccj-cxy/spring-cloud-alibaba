package com.snk.auth.service.impl;

import com.snk.auth.pojo.enums.LoginType;
import com.snk.auth.service.LoginService;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;
import org.springframework.stereotype.Component;

import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

/**
 * 策略工厂，从spring上下文中提取策略
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Date : 2021/6/1
 */
@Component
public class LoginStrategyFactory implements InitializingBean, ApplicationContextAware {
    private final Map<LoginType, LoginService> strategyMap = new ConcurrentHashMap<>();
    private ApplicationContext appContext;

    @Override
    public void afterPropertiesSet() throws Exception {
        // 将 Spring 容器中所有的 LoginHandler 注册到 strategyMap
        appContext.getBeansOfType(LoginService.class)
                .values().forEach(hander->strategyMap.put(hander.getLoginType(),hander));
    }

    @Override
    public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {
        appContext = applicationContext;
    }

    public LoginService getStrategy(LoginType loginType) {
        return strategyMap.get(loginType);
    }
}
