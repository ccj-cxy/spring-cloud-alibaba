package com.snk.core;

import com.snk.common.CommonAdviceSelectImport;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : springboot自动注入类
 * @Date : 2021/5/21
 */
@Configuration
@Import(CommonAdviceSelectImport.class)
public class SnkBootAutoConfiguration {

}
