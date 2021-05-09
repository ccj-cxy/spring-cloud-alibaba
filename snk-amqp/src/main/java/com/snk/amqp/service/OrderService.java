package com.snk.amqp.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.UUID;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 订单服务
 * @Date : 2021/5/7
 */
@Service
@Slf4j
public class OrderService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    public void makeOrder(String userId,String productId,int num ) {
        //1.根据商品id查询库存是否充足
        //2.保存订单
        String orderId = UUID.randomUUID().toString();
        log.info("订单生产成功:{}",orderId);
        //3.通过mqq来完成消息的分发
        //参数1：交换机 参数2：路由key/queue队列名称  参数3：消息内容
        String exchangeName ="fanout_order_exchange";
        String routingKey = "";
        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
    }


    public void makeOrderDirect(String userId,String productId,int num ) {
        //1.根据商品id查询库存是否充足
        //2.保存订单
        String orderId = UUID.randomUUID().toString();
        log.info("订单生产成功:{}",orderId);
        //3.通过mqq来完成消息的分发
        //参数1：交换机 参数2：路由key/queue队列名称  参数3：消息内容
        String exchangeName ="direct_order_exchange";
        rabbitTemplate.convertAndSend(exchangeName,"email",orderId);
        rabbitTemplate.convertAndSend(exchangeName,"duanxin",orderId);
    }

    public void makeOrderTopic(String userId,String productId,int num ) {
        //1.根据商品id查询库存是否充足
        //2.保存订单
        String orderId = UUID.randomUUID().toString();
        log.info("订单生产成功:{}",orderId);
        //3.通过mqq来完成消息的分发
        //参数1：交换机 参数2：路由key/queue队列名称  参数3：消息内容
        String exchangeName ="topic_order_exchange";
        String routingKey = "com.email.duanxin";
        //#表示有或者没有模糊匹配，*表示只有一级
        //#.duanxin.#,	*.email.#,	com.#(sms)
        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderId);
    }
}
