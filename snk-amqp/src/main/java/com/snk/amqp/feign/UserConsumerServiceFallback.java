package com.snk.amqp.feign;

import com.snk.common.exception.BusinessException;
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
        return brokerMessageLogDTO-> {
            log.error("接口调用异常{}","modifyMessageStatus");
            throw new BusinessException("服务调用异常");
        };
    }
}