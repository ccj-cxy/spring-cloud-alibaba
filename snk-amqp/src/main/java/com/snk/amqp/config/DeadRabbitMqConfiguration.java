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
public class DeadRabbitMqConfiguration {

    //1.声明注册fanout模式的交换机
    @Bean
    public DirectExchange deadExchange() {
        return new DirectExchange("dead_direct_exchange",true,false);
    }

    //2.声明队列 sms.direct.queue email.direct.queue duanxin.direct.queue
    @Bean
    public Queue deadQueue(){
        return new Queue("dead.direct.queue",true);
    }



    @Bean
    public Binding deadBinding() {
        return BindingBuilder.bind(deadQueue()).to(deadExchange()).with("dead");
    }


}
