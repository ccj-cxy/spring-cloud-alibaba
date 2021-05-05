package com.snk.email.controller;

import com.snk.common.web.AjaxResult;
import com.snk.email.domain.OrdinaryEmailDTO;
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
public class MailController {
    @Autowired
    EmailService emailService;

    @PostMapping(value = "email")
    @ResponseBody
    public AjaxResult comment(HttpServletRequest request, HttpServletResponse response,
                              @RequestBody OrdinaryEmailDTO dto) {
        //发送邮件
        OrdinaryEmailDTO ordinaryEmailDTO = emailService.sendMail(dto);
        if (StringUtils.equals(ordinaryEmailDTO.getStatus(),"ok")) {
            return AjaxResult.success();
        }
       return AjaxResult.error("邮件发送失败");
    }
}
