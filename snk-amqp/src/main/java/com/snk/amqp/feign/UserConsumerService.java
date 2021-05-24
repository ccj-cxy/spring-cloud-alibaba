package com.snk.amqp.feign;

import com.snk.amqp.pojo.domain.BrokerMessageLogDTO;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 *
 * @author Cai.ChangJun
 * @version 1.0.0
 * @Date 2021/5/18 16:42
 */
@FeignClient(value = "user-consumer",fallbackFactory = UserConsumerServiceFallback.class)
@Component
public interface UserConsumerService {
    /**
     * 修改消息记录表状态
     * @author Cai.ChangJun
     * @param brokerMessageLogDTO  修改条件
     * @return 是否修改成功
     * @version 1.0.0
     * @Date 2021/5/18 16:45
     */
    @PostMapping(value = "/brokerMessageLog/modifyMessageStatus", produces = "application/json; charset=UTF-8")
    Boolean modifyMessageStatus(@RequestBody BrokerMessageLogDTO brokerMessageLogDTO);
}