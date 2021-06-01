package com.snk.auth.service;

import com.snk.auth.pojo.dto.UserDTO;
import com.snk.auth.pojo.enums.LoginType;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 登录服务
 * @Date : 2021/6/1
 */
public interface LoginService {
    /**
     * 获取登录类型
     * @description:
     * @author Cai.ChangJun
     * @return: 登录类型
     * @version 1.0.0
     * @Date 2021/6/1 17:35
     */
    LoginType getLoginType();


    /**
     * 登录并获得token一小时过期 缓存登录对象半小时过期
     * @author Cai.ChangJun
     * @param username : 用户名
     * @param password : 密码
     * @return UserDTO : 登录对象抽象
     * @version 1.0.0
     * @Date 2021/5/26 23:48
     */
    UserDTO login(String username, String password);



}
