package com.snk.common.webRetrun;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.StringHttpMessageConverter;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import java.util.List;

/**
 * StringHttpMessageConverter 要比其它的 Converter 排得靠前一些。我们需要将处理 Object 类型的 HttpMessageConverter 放得靠前一些
 * @author Cai.ChangJun
 * @version 1.0.0
 * @Date 2021/5/17 22:57
 */
@Configuration
public class WebConfiguration implements WebMvcConfigurer {

    @Override
    public void configureMessageConverters(List<HttpMessageConverter<?>> converters) {
        // 第一种方式是将 json 处理的转换器放到第一位并且是继承GenericHttpMessageConverter，这样json转换器就会进行处理 String转换器就处理不了了。
        //converters.add(0, new MappingJackson2HttpMessageConverter());
        // 第二种就是把String类型的转换器去掉，会找到后面的转换器进行转换
        converters.removeIf(httpMessageConverter -> httpMessageConverter.getClass() == StringHttpMessageConverter.class);
    }
}