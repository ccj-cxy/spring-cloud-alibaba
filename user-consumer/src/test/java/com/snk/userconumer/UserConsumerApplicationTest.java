package com.snk.userconumer;

import cn.hutool.http.HttpUtil;
import com.snk.common.utils.IpUtil;
import com.snk.jwt.utils.JWTUtil;
import com.snk.redis.utils.RedisUtil;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.UUID;

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


    @Autowired
    JWTUtil jwtUtil;
    @Test
    void authTest() {
        jwtUtil.createJWT(UUID.randomUUID().toString(),"123",null);
    }



    @Test
    void test11() {

        String body = HttpUtil.createGet("https://api.qimai.cn/appDetail/keywordDetail?analysis=eEcbVxFAdFVEUVlcFw9SQRNbRAV0VURRWVxwG1UGCFAHB1AGCAABBXATCQ%3D%3D")
//                .header("Authorization", "Basic " )
                .execute()
                .charset("utf-8")
                .body();
        System.out.println(body);
    }

}
