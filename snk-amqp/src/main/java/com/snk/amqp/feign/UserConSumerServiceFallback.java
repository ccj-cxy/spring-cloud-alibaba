package com.snk.amqp.feign;

import com.snk.amqp.pojo.domain.BrokerMessageLogDTO;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class UserConSumerServiceFallback implements FallbackFactory<UserConSumerService> {



    @Override
    public UserConSumerService create(Throwable throwable) {
        return new UserConSumerService() {
            @Override
            public Boolean modifyMessageStatus(BrokerMessageLogDTO brokerMessageLogDTO) {
                throw new RuntimeException("服务调用失败");
            }
        };
    }
}