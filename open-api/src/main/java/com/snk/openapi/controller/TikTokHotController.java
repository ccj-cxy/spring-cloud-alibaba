package com.snk.openapi.controller;

import io.swagger.annotations.Api;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description :
 * @Date : 2022/6/26
 */
@RestController
@Api(value = "抖音热门视频",tags = "抖音热门视频")
public class TikTokHotController {
    @Autowired
    RestTemplate restTemplate;
    @GetMapping("/tikTok")
    public Map<String,Object> getHotVideos() {
        ResponseEntity<Map> forEntity = restTemplate.getForEntity("https://creator.douyin.com/aweme/v1/creator/data/billboard/?billboard_type=4",
                Map.class);
        return forEntity.getBody();
    }
}
