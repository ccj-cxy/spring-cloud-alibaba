package com.snk.userconumer.feign.amqp;

import com.snk.userconumer.pojo.domain.Order;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;

/**
 * 调用amqp服务接口
 * @author Cai.ChangJun
 * @version 1.0.0
 * @Date 2021/5/16 0:45
 */
@FeignClient(name = "rabbit-service",fallbackFactory = OrderServiceFallback.class)
public interface OrderFeignService {
    @PostMapping(value = "/order/send", produces = "application/json; charset=UTF-8")
    String orderSend(Order orderDO);
}