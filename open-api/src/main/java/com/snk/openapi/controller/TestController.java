package com.snk.openapi.controller;

import io.swagger.annotations.Api;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description :
 * @Date : 2022/6/26
 */
@RestController
@Api(value = "测试",tags = "测试")
public class TestController {

    @GetMapping("/")
    public String hello() {
        return "hello openApi";
    }
}
