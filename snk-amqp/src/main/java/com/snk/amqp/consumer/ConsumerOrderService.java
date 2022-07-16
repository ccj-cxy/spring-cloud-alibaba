package com.snk.amqp.consumer;

import cn.hutool.json.JSONUtil;
import com.rabbitmq.client.Channel;
import com.snk.amqp.config.order.OrderFanoutConfig;
import com.snk.amqp.feign.UserConsumerService;
import com.snk.amqp.pojo.domain.BrokerMessageLogDTO;
import com.snk.amqp.pojo.domain.OrderDTO;
import com.snk.redis.utils.RedisUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.Assert;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.util.concurrent.TimeUnit;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 订单服务消费者
 * @Date : 2021/5/18
 */
@Service
@Slf4j
public class ConsumerOrderService {
    @Autowired
    private UserConsumerService userConsumerService;

    @Autowired
    RedisUtil redisUtil;


    @RabbitListener(queues = OrderFanoutConfig.ORDER_QUEUE)
    public void consumeOrder(Message message, Channel channel)  {
        OrderDTO orderDTO = null;
        try {
            orderDTO = JSONUtil.toBean( new String(message.getBody(),"UTF-8"), OrderDTO.class);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

        try {
            Assert.notNull(orderDTO,"消费的消息为异常消息为空");
            //利用redis setnx命令 设置key没有返回true 有则返回false特性完成接口幂等//设置过期时间10秒移除key
            if (redisUtil.setIfAbsent(orderDTO.getOrderId(),orderDTO.getOrderName())
                    && redisUtil.expire(orderDTO.getOrderId(),10, TimeUnit.SECONDS)) {
                //手动确认消息
                channel.basicAck(message.getMessageProperties().getDeliveryTag(), false);
                //确认接收修改冗余记录
                BrokerMessageLogDTO brokerMessageLogDTO = new BrokerMessageLogDTO().setMessageId(orderDTO.getOrderId())
                        .setStatus("3");
                userConsumerService.modifyMessageStatus(brokerMessageLogDTO);
                log.info("消费消息{}",orderDTO);
            }
        } catch (IOException e) {
            //删除key 启动重试机制 指定次数后移动到死信队列中
            redisUtil.delete(orderDTO.getOrderId());
            log.error("接收mq消息失败:{}",message);
            throw new RuntimeException("消费异常");
        }
    }
}
