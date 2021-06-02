package com.snk.auth.service.impl;

import com.snk.auth.feign.EmailFeign;
import com.snk.auth.pojo.dto.OrdinaryEmailDTO;
import com.snk.common.webRetrun.Response;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 发送邮件服务
 * @Date : 2021/6/2
 */
@Component
@Slf4j
public class SendEmailService {
    @Autowired
    private EmailFeign emailFeign;

    /**
     *一、异步方法使用static修饰
     * 二、异步类没有使用@Component注解（或其他注解）导致spring无法扫描到异步类
     * 三、异步方法不能与异步方法在同一个类中
     * 四、类中需要使用@Autowired或@Resource等注解自动注入，不能自己手动new对象
     * 五、如果使用SpringBoot框架必须在启动类中增加@EnableAsync注解
     * 六、在Async 方法上标注@Transactional是没用的。 在Async 方法调用的方法上标注@Transactional 有效。
     * 七、调用被@Async标记的方法的调用者不能和被调用的方法在同一类中不然不会起作用！！！！！！！
     * 八、使用@Async时要求是不能有返回值的不然会报错的 因为异步要求是不关心结果的
     */
    @Async
    public void sendEmail(OrdinaryEmailDTO ordinaryEmailDTO) {
        try {
            Thread.sleep(3000);
            log.info("发送邮件至{}",ordinaryEmailDTO.getTo());
            Response response = emailFeign.sendEmail(ordinaryEmailDTO);
            log.info("{}",response);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
