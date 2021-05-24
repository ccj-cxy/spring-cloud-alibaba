package com.snk.userconumer;

import com.snk.common.utils.IpUtil;
import com.snk.redis.utils.RedisUtil;
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
public class UserConsumerApplicationTest {
    @Autowired
    RedisUtil redisUtil;

    @Test
    void test() {
        redisUtil.set("test","ces");
    }

    @Test
    void test1() {
        System.out.println(redisUtil);
    }

    @Test
    void ipTest() {
        System.out.println(IpUtil.getCityInfo("78.142.192.245"));
    }
}
