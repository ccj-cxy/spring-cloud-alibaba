package com.snk.common.webRetrun;

import org.springframework.context.annotation.Configuration;
import org.springframework.http.converter.HttpMessageConverter;
import org.springframework.http.converter.json.MappingJackson2HttpMessageConverter;
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
        converters.add(0, new MappingJackson2HttpMessageConverter());
    }
}