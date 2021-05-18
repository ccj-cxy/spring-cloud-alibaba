package com.snk.email.controller;

import com.snk.common.webRetrun.Response;
import com.snk.email.domain.OrdinaryEmailDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import com.snk.email.service.EmailService;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 邮件API
 * @Date : 2021/5/5
 */
@RestController
@Slf4j
public class MailController {
    @Autowired
    EmailService emailService;

    @PostMapping(value = "email")
    @ResponseBody
    public Response comment(HttpServletRequest request, HttpServletResponse response,
                            @RequestBody OrdinaryEmailDTO dto) {
        //发送邮件
        OrdinaryEmailDTO ordinaryEmailDTO = emailService.sendMail(dto);
        if (StringUtils.equals(ordinaryEmailDTO.getStatus(),"ok")) {
            log.info("邮件发送成功！发送邮箱至{}",ordinaryEmailDTO.getTo());
            return new Response("邮件发送成功！");
        }
        log.error("发送邮箱至{},邮件发送失败！",ordinaryEmailDTO.getTo());
       return new Response("邮件发送失败！");
    }
}
