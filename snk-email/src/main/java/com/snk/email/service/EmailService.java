package com.snk.email.service;

import com.snk.email.domain.EmailDTO;
import com.snk.email.domain.EmailFromDTO;
import com.snk.email.domain.OrdinaryEmailDTO;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.util.Date;
import java.util.Map;
import java.util.Objects;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 邮件相关服务
 * @Date : 2021/5/5
 */
@Service
@Slf4j
public class EmailService {
    @Autowired
    /**注入邮件工具类*/
    private JavaMailSenderImpl mailSender;
    @Value("${spring.mail.username}")
    private String from;

    /**
     *     template模板引擎
     */
    @Autowired
    private TemplateEngine templateEngine;

    /**
     * 验证邮件发送dto 不为空时对JavaMailSenderImpl进行配置
     * @description:
     * @author Cai.ChangJun
     * @param null:
     * @return:
     * @version 1.0.0
     * @Date 2021/7/4 17:21
     */
    private void verificationEntity(EmailFromDTO emailFromDTO) {
        if (Objects.nonNull(emailFromDTO)) {
            mailSender.setUsername(emailFromDTO.getUsername());
            mailSender.setPassword(emailFromDTO.getPassword());
            mailSender.setHost(emailFromDTO.getHost());
            mailSender.setJavaMailProperties(emailFromDTO.getProperties());
        }
    }

    /**
     * 发送文本邮件
     *
     * @param emailDTO 邮件发送所需条件
     * @param emailFromDTO 邮件发送额外条件
     */
    @Async
    public void sendSimpleMail(EmailDTO emailDTO, EmailFromDTO emailFromDTO) {
        verificationEntity(emailFromDTO);
        SimpleMailMessage message = new SimpleMailMessage();
        //发件人
        message.setFrom(from);
        //收件人，可变数组可以一次性发送多个人
        message.setTo(emailDTO.getTo());
        message.setSubject(emailDTO.getSubject());
        message.setText(emailDTO.getContent());
        mailSender.send(message);
    }

    /**
     * 发送html邮件
     */
    @Async
    public void sendHtmlMail(EmailDTO emailDTO, EmailFromDTO emailFromDTO) {
        verificationEntity(emailFromDTO);
        try {
            //注意这里使用的是MimeMessage
            MimeMessage message = mailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            if (Objects.isNull(emailFromDTO)) {
                helper.setFrom(from);
            }
            helper.setTo(emailDTO.getTo());
            helper.setSubject(emailDTO.getSubject());
            //第二个参数：格式是否为html
            helper.setText(emailDTO.getContent(), true);
            mailSender.send(message);
        }catch (MessagingException e){
            log.error("发送邮件时发生异常！", e);
        }
    }

    /**
     * 发送模板邮件
     * @param to
     * @param subject
     * @param template
     */
    public void sendTemplateMail(EmailDTO emailDTO , EmailFromDTO emailFromDTO,Map<String,Object> values,String template){
        verificationEntity(emailFromDTO);
        try {
            //使用模板thymeleaf
            Context context = new Context();
            //定义模板数据
            context.setVariables(values);
            // 获得模板
            String emailContent = templateEngine.process(template,context);
            emailDTO.setContent(emailContent);
            //设置邮件内容
            JavaMailSenderImpl javaMailSend = new JavaMailSenderImpl();
            MimeMessage message = javaMailSend.createMimeMessage();
            MimeMessageHelper messageHelper = new MimeMessageHelper(message, true, "utf-8");
            messageHelper.setText(emailContent,true);

            // 该方法本质上还是发送html邮件，调用之前发送html邮件的方法
            this.sendHtmlMail(emailDTO, emailFromDTO);
        }  catch (MessagingException e) {
            log.error("发送邮件时发生异常！", e);
        }
    }


    @Async
    public OrdinaryEmailDTO sendMail(OrdinaryEmailDTO mailVo) {
        try {
            //1.检测邮件
            checkMail(mailVo);
            //2.发送邮件
            sendMimeMail(mailVo);
            //3.保存邮件
            return saveMail(mailVo);
        } catch (Exception e) {
            mailVo.setStatus("fail");
            mailVo.setError(e.getMessage());
            return mailVo;
        }
    }

    /**
     * 检测邮件信息类
     */
    private void checkMail(OrdinaryEmailDTO mailVo) {
        if (StringUtils.isEmpty(mailVo.getTo())) {
            throw new RuntimeException("邮件收信人不能为空");
        }
        if (StringUtils.isEmpty(mailVo.getSubject())) {
            throw new RuntimeException("邮件主题不能为空");
        }
        if (StringUtils.isEmpty(mailVo.getText())) {
            throw new RuntimeException("邮件内容不能为空");
        }
    }

    //构建复杂邮件信息类
    private void sendMimeMail(OrdinaryEmailDTO mailVo) {
        try {
            //true表示支持复杂类型
            MimeMessageHelper messageHelper = new MimeMessageHelper(mailSender.createMimeMessage(), true);
            //邮件发信人从配置项读取
            //mailVo.setFrom(getMailSendFrom());
            //邮件发信人
            messageHelper.setFrom(from);
            //邮件收信人
            messageHelper.setTo(mailVo.getTo().split(","));
            //邮件主题
            messageHelper.setSubject(mailVo.getSubject());
            //邮件内容
            messageHelper.setText(mailVo.getText());
            //抄送
            if (!StringUtils.isEmpty(mailVo.getCc())) {
                messageHelper.setCc(mailVo.getCc().split(","));
            }
            //密送
            if (!StringUtils.isEmpty(mailVo.getBcc())) {
                messageHelper.setCc(mailVo.getBcc().split(","));
            }
            //添加邮件附件
            if (mailVo.getMultipartFiles() != null) {
                for (MultipartFile multipartFile : mailVo.getMultipartFiles()) {
                    messageHelper.addAttachment(multipartFile.getOriginalFilename(), multipartFile);
                }
            }
            //发送时间
            if (Objects.isNull(mailVo.getSentDate())) {
                mailVo.setSentDate(new Date());
                messageHelper.setSentDate(mailVo.getSentDate());
            }
            //正式发送邮件
            mailSender.send(messageHelper.getMimeMessage());
            mailVo.setStatus("ok");
        } catch (Exception e) {
            //发送失败
            throw new RuntimeException(e);
        }
    }

    //保存邮件
    private OrdinaryEmailDTO saveMail(OrdinaryEmailDTO mailVo) {

        //将邮件保存到数据库..

        return mailVo;
    }

    //获取邮件发信人
    public String getMailSendFrom() {
        return mailSender.getJavaMailProperties().getProperty("from");
    }

}
