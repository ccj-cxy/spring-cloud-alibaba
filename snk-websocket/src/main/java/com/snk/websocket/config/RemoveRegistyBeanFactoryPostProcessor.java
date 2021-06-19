package com.snk.websocket.config;

import org.springframework.beans.BeansException;
import org.springframework.beans.factory.config.ConfigurableListableBeanFactory;
import org.springframework.beans.factory.support.BeanDefinitionRegistry;
import org.springframework.beans.factory.support.BeanDefinitionRegistryPostProcessor;
import org.springframework.context.annotation.Configuration;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 删除项目不需要的多余配置bean
 * @Date : 2021/6/19
 */
@Configuration
public class RemoveRegistyBeanFactoryPostProcessor implements BeanDefinitionRegistryPostProcessor {
    @Override
    public void postProcessBeanDefinitionRegistry(BeanDefinitionRegistry beanDefinitionRegistry) throws BeansException {
        if (beanDefinitionRegistry.containsBeanDefinition("corsFilter")){
            beanDefinitionRegistry.removeBeanDefinition("corsFilter");
        }

    }

    @Override
    public void postProcessBeanFactory(ConfigurableListableBeanFactory configurableListableBeanFactory) throws BeansException {

    }
}
