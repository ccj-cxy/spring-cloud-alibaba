package com.snk.userconumer.feign;

import com.snk.common.exception.BusinessException;
import feign.hystrix.FallbackFactory;
import org.springframework.stereotype.Service;

/**
 *
 * @author Cai.ChangJun
 * @version 1.0.0
 * @Date 2021/5/21 15:28
 */
@Service
public class UserServiceFallback implements FallbackFactory<UserService> {

    @Override
    public UserService create(Throwable throwable) {
        throw new BusinessException("远程调用运单服务失败，消息无法投递");
    }
}