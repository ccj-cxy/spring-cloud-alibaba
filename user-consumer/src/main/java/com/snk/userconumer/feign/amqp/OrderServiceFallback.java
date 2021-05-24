package com.snk.userconumer.feign.amqp;

import com.snk.common.exception.BusinessException;
import com.snk.userconumer.pojo.domain.Order;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;
/**
 * feign接口方法回调类
 * @author Cai.ChangJun
 * @version 1.0.0
 * @Date 2021/5/16 0:51
 */
@Component
@Slf4j
public class OrderServiceFallback implements FallbackFactory<OrderFeignService> {


    @Override
    public OrderFeignService create(Throwable throwable) {
        return new OrderFeignService() {
            @Override
            public String orderSend(Order orderDO) {
                log.error("接口调用异常{}","orderSend");
                throw new BusinessException("服务调用异常");
            }
        };
    }
}