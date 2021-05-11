package com.snk.amqp;

import com.snk.amqp.service.OrderService;
import com.snk.amqp.service.fanout.FanoutSmsConsumer;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description :
 * @Date : 2021/5/7
 */
@SpringBootTest
public class AmqpApplicationTest {
    @Autowired
    OrderService orderService;
    @Autowired
    FanoutSmsConsumer fanoutSmsConsumer;

    @Test
    void contextLoads() {
        orderService.makeOrder("1","1",12);
    }

    @Test
    void testDirect() {
        orderService.makeOrderDirect("1","1",12);
    }

    @Test
    void testTopic() {
        orderService.makeOrderTopic("1","1",12);
    }

    @Test
    void testTTL() {
        for (int i = 0; i < 6; i++) {
            orderService.makeOrderTtl("1","1",12);
        }
    }

    @Test
    void testTTLMessage() {
        orderService.makeOrderTtlMessage("1","1",12);
    }


}
