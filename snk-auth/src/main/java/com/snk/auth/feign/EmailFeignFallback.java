package com.snk.auth.feign;

import com.snk.auth.pojo.dto.OrdinaryEmailDTO;
import com.snk.common.exception.BusinessException;
import com.snk.common.webRetrun.Response;
import feign.hystrix.FallbackFactory;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description :
 * @Date : 2021/6/2
 */
@Component
@Slf4j
public class EmailFeignFallback  implements FallbackFactory<EmailFeign> {
    @Override
    public EmailFeign create(Throwable throwable) {
        return new EmailFeign() {
            @Override
            public Response sendEmail(OrdinaryEmailDTO ordinaryEmailDTO) {
                log.error("接口调用异常{}","sendEmail");
                throw new BusinessException("服务调用异常");
            }
        };
    }
}
