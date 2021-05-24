package com.snk.amqp.config.order;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.FanoutExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 订单服务队列交换机绑定规则
 * @Date : 2021/5/16
 */
@Configuration
public class OrderFanoutConfig {
    public static final String ORDER_FANOUT_EXCHANGE = "snk_order_fanout_exchange";
    public static final String ORDER_QUEUE = "snk.order.queue";

    /**声明fanout模式交换机*/
    @Bean
    public FanoutExchange orderFanoutExchange() {
        return new FanoutExchange(ORDER_FANOUT_EXCHANGE,true,false);
    }

    /**声明订单队列*/
    @Bean
    public Queue orderQueue(){
        return new Queue(ORDER_QUEUE,true);
    }

    /**什么绑定关系*/
    @Bean
    public Binding orderBinding() {
        return BindingBuilder.bind(orderQueue()).to(orderFanoutExchange());
    }
}
