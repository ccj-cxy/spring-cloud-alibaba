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
@RabbitListener(queues = "duanxin.fanout.queue")
@Slf4j
public class FanoutDuanxinConsumer {

    @RabbitHandler
    public void reviceMessage(String message) {
        log.info("duanxin.fanout.queue---->接收到了订单消息：{}",message);
    }
}
