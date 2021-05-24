package com.snk.amqp.provider.controller;

import com.snk.amqp.pojo.domain.OrderDTO;
import com.snk.amqp.provider.service.SendOrderService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 订单消息投递控制器
 * @Date : 2021/5/16
 */
@RestController
@RequestMapping("/order")
@Slf4j
public class SendOrderController {

    @Autowired
    private SendOrderService sendOrderService;

    @PostMapping("/send")
    public String sendOrderMessage(@RequestBody OrderDTO orderDTO) {
        log.info("准备开始投递消息");
        return sendOrderService.sendOrder(orderDTO);
    }
}
