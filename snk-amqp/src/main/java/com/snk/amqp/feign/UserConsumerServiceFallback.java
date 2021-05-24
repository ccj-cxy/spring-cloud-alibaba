package com.snk.amqp.feign;

import com.snk.amqp.pojo.domain.BrokerMessageLogDTO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
/**
 * @author Cai.ChangJun
 * @version 1.0.0
 * @Date 2021/5/24 15:43
 */
@Component
@Slf4j
public class UserConsumerServiceFallback implements FallbackFactory<UserConsumerService> {



    @Override
    public UserConsumerService create(Throwable throwable) {
        return new UserConsumerService() {
            @Override
            public Boolean modifyMessageStatus(BrokerMessageLogDTO brokerMessageLogDTO) {
                throw new RuntimeException("服务调用失败");
            }
        };
    }
}