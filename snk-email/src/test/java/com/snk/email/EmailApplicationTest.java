package com.snk.email;

import com.snk.email.domain.EmailDTO;
import com.snk.email.service.EmailService;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashMap;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 测试类
 * @Date : 2021/7/4
 */
@SpringBootTest
public class EmailApplicationTest {

    @Autowired
    private EmailService emailService;

    @Test
    void testSend() {
        EmailDTO emailDTO = new EmailDTO().setTo(new String[]{"caichangjun@qindiankeji.cn"}).setSubject("测试邮件").setContent("这是一封测试邮件");
        HashMap<String, Object> map = new HashMap<>();
        map.put("project","SNK");
        map.put("author","ccj");
        map.put("code","1111");
        String template = "mail";
        emailService.sendTemplateMail(emailDTO,null,map,template);
    }
}
