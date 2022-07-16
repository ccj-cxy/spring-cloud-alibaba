package com.snk.amqp;

import com.snk.amqp.feign.UserConsumerService;
import com.snk.amqp.pojo.domain.BrokerMessageLogDTO;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description :
 * @Date : 2021/5/18
 */
@SpringBootTest
public class FeignTest {

    @Autowired
    UserConsumerService userConsumerService;

    @Test
    void test() {
       userConsumerService.modifyMessageStatus(new BrokerMessageLogDTO().setMessageId("149732447200874496").setStatus("1"));
    }
}
