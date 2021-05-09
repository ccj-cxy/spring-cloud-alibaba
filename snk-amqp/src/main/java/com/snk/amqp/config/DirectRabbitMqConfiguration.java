package com.snk.amqp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : rabbitMQ配置配
 * @Date : 2021/5/7
 */
@Configuration
public class DirectRabbitMqConfiguration {

    //1.声明注册fanout模式的交换机
    @Bean
    public DirectExchange directExchange() {
        return new DirectExchange("direct_order_exchange",true,false);
    }

    //2.声明队列 sms.direct.queue email.direct.queue duanxin.direct.queue
    @Bean
    public Queue smsDirectQueue(){
        return new Queue("sms.direct.queue",true);
    }

    @Bean
    public Queue emailDirectQueue(){
        return new Queue("email.direct.queue",true);
    }

    @Bean
    public Queue duanxinDirectQueue(){
        return new Queue("duanxin.direct.queue",true);
    }
    //3.完成绑定关系
    @Bean
    public Binding smsDirectBinding() {
        return BindingBuilder.bind(smsDirectQueue()).to(directExchange()).with("sms");
    }

    @Bean
    public Binding emailDirectBinding() {
        return BindingBuilder.bind(emailDirectQueue()).to(directExchange()).with("email");
    }

    @Bean
    public Binding duanxinDirectBinding() {
        return BindingBuilder.bind(duanxinDirectQueue()).to(directExchange()).with("duanxin");
    }
}
