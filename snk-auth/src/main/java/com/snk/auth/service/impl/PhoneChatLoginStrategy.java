package com.snk.auth.service.impl;

import com.snk.auth.pojo.dto.UserDTO;
import com.snk.auth.pojo.enums.LoginType;
import com.snk.auth.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 手机
 * @Date : 2021/6/1
 */
@Component
@Slf4j
public class PhoneChatLoginStrategy implements LoginService {
    @Override
    public LoginType getLoginType() {
        return LoginType.PHONE;
    }

    @Override
    public UserDTO login(String username, String password) {
        //TODO 未实现手机登录
        log.info("手机登录成功");
        return new UserDTO();
    }
}
