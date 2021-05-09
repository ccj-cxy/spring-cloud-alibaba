package com.snk.amqp.service.fanout;

import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Service;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description :
 * @Date : 2021/5/7
 */
@Service
@RabbitListener(queues = "sms.fanout.queue")
@Slf4j
public class FanoutSmsConsumer {

    @RabbitHandler
    public void reviceMessage(String message) {
        log.info("sms.fanout.queue---->接收到了订单消息：{}",message);
    }
}
