package com.snk.userconumer.controller;

import com.snk.common.utils.QrCodeUtil;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 二维码生成工具测试
 * @Date : 2021/5/31
 */
@RestController
@RequestMapping("/qrCode")
@Api(value = "二维码" ,tags = "获取二维码")
public class QrCodeController {

    @GetMapping("/{context}")
    @ApiOperation(value = "获取二维码",notes = "传入生成内容，生成二维码")
    public void getQrCode(@PathVariable String context, HttpServletResponse response) {
        QrCodeUtil.outputPng(context,response);
    }
}
