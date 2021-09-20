package com.snk.auth.controller;

import com.google.code.kaptcha.impl.DefaultKaptcha;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.imageio.ImageIO;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.image.BufferedImage;
import java.io.ByteArrayOutputStream;

/**
 * @author wt
 * @create 2020.11.10
 * @description 验证码
 */
@Slf4j
@RestController
@Api(value = "验证码",tags = "获取验证码")
public class KaptchaController {

    @Autowired
    private DefaultKaptcha defaultKaptcha;


    @RequestMapping("/kaptcha")
    @ApiOperation(value = "验证吗" ,notes = "获取验证码")
    public void createKaptcha(HttpServletRequest request, HttpServletResponse response) throws Exception{
        //字符串存入session
        String text = defaultKaptcha.createText();
        //TODO 存入redis
        ByteArrayOutputStream byteArrayOutputStream = new ByteArrayOutputStream();
        BufferedImage bufferedImage = defaultKaptcha.createImage(text);
        ImageIO.write(bufferedImage, "jpg", byteArrayOutputStream);
        byte[] captchaChallengeAsJpeg = byteArrayOutputStream.toByteArray();
        response.setContentType("image/jpeg");
        response.getOutputStream().write(captchaChallengeAsJpeg);
    }
}
