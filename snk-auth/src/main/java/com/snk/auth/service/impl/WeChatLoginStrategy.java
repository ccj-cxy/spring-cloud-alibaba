package com.snk.auth.service.impl;

import com.snk.auth.pojo.dto.UserDTO;
import com.snk.auth.pojo.enums.LoginType;
import com.snk.auth.service.LoginService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 微信登录
 * @Date : 2021/6/1
 */
@Component
@Slf4j
public class WeChatLoginStrategy  implements LoginService {
    @Override
    public LoginType getLoginType() {
        return LoginType.WE_CHAT;
    }

    @Override
    public UserDTO login(String username, String password) {
        //TODO 未实现微信登录
        log.info("微信登录成功");
        return new UserDTO();
    }
}
