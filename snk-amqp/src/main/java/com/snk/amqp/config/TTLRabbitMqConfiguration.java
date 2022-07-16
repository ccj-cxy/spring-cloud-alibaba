package com.snk.amqp.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.HashMap;
import java.util.Map;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : rabbitMQ配置配 延时队列
 * @Date : 2021/5/7
 */
@Configuration
public class TTLRabbitMqConfiguration {

    //1.声明注册fanout模式的交换机
    @Bean
    public DirectExchange ttlExchange() {
        return new DirectExchange("ttl_direct_exchange",true,false);
    }

    //2.声明队列 sms.direct.queue email.direct.queue duanxin.direct.queue
    @Bean
    public Queue ttlDirectQueue(){
        Map<String,Object> args = new HashMap<>();
        //这里是一个int类型
        args.put("x-message-ttl",5000);
        //设置最大消息容量
        args.put("x-max-length",5);

        //设置死信队列
        args.put("x-dead-letter-exchange","dead_direct_exchange");
        args.put("x-dead-letter-routing-key","dead");
        return new Queue("ttl.direct.queue",true,false,false,args);
    }

    @Bean
    public Queue ttlMessageDirectQueue(){
        return new Queue("ttl.message.direct.queue",true);
    }


    //3.完成绑定关系
    @Bean
    public Binding ttlDirectBinding() {
        return BindingBuilder.bind(ttlDirectQueue()).to(ttlExchange()).with("ttl");
    }

    @Bean
    public Binding ttlMessageDirectBinding() {
        return BindingBuilder.bind(ttlMessageDirectQueue()).to(ttlExchange()).with("ttlmessage");
    }


}
