package com.snk.amqp.provider.service;

import cn.hutool.json.JSONUtil;
import com.snk.amqp.feign.UserConSumerService;
import com.snk.amqp.pojo.domain.BrokerMessageLogDTO;
import com.snk.amqp.pojo.domain.OrderDTO;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.rabbit.connection.CorrelationData;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.concurrent.atomic.AtomicReference;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 发送订单消息
 * @Date : 2021/5/16
 */
@Service
@Slf4j
public class SendOrderService {
    @Autowired
    private RabbitTemplate rabbitTemplate;

    @Autowired
    private UserConSumerService userConSumerService;

    /**在构造函数调用之后处理*/
    @PostConstruct
    public void regCallback() {
        rabbitTemplate.setConfirmCallback(new RabbitTemplate.ConfirmCallback() {
            @Override
            public void confirm(CorrelationData correlationData, boolean ack, String cause) {
                log.info("cause:{}",cause);
                String orderId = correlationData.getId();
                if (!ack) {
                    log.error("消息应答失败，orderId是：{}",orderId);
                    //TODO 消息应答失败修改消息记录表消息状态
                    Date date = new Date();
                    BrokerMessageLogDTO brokerMessageLogDTO = new BrokerMessageLogDTO().setMessageId(orderId)
                            .setStatus("2").setNextRetry(new Date(date.getTime() + 10 * 60 * 1000));
                    userConSumerService.modifyMessageStatus(brokerMessageLogDTO);
                }
                //TODO 消息应答成功修改消息记录表 try catch 修改失败耶记录
                try {
                    BrokerMessageLogDTO brokerMessageLogDTO = new BrokerMessageLogDTO().setMessageId(orderId)
                            .setStatus("1");
                    userConSumerService.modifyMessageStatus(brokerMessageLogDTO);
                }catch (Exception e){
                    log.error("消息应答失败，orderId是：{}",orderId);
                    Date date = new Date();
                    BrokerMessageLogDTO brokerMessageLogDTO = new BrokerMessageLogDTO().setMessageId(orderId)
                            .setStatus("2").setNextRetry(new Date(date.getTime() + 10 * 60 * 1000));
                    userConSumerService.modifyMessageStatus(brokerMessageLogDTO);
                }
            }
        });
    }


    public String sendOrder(OrderDTO orderDTO) {
        log.info("消息开始投递:{}", JSONUtil.toJsonStr(JSONUtil.parse(orderDTO)));
        //3.通过mqq来完成消息的分发
        //参数1：交换机 参数2：路由key/queue队列名称  参数3：消息内容
        String exchangeName ="snk_order_fanout_exchange";
        String routingKey = "";
        rabbitTemplate.convertAndSend(exchangeName,routingKey,orderDTO,new CorrelationData(orderDTO.getOrderId()));
        log.info("消息投递成功");
        return orderDTO.getOrderId();
    }
}
