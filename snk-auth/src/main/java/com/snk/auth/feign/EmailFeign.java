package com.snk.auth.feign;

import com.snk.auth.pojo.dto.OrdinaryEmailDTO;
import com.snk.common.webRetrun.Response;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 调用email服务
 * @Date : 2021/6/2
 */
@FeignClient(name = "email-service",fallbackFactory = EmailFeignFallback.class)
public interface EmailFeign {

    /**
     * 发送邮件
     * @author Cai.ChangJun
     * @param ordinaryEmailDTO: 发送邮件实体
     * @return:
     * @version 1.0.0
     * @Date 2021/6/2 15:35
     */
    @PostMapping(value = "email")
    Response sendEmail(@RequestBody OrdinaryEmailDTO ordinaryEmailDTO);
}
