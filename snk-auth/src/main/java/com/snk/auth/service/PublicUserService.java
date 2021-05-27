package com.snk.auth.service;

import com.snk.auth.pojo.domain.PublicUser;
import com.baomidou.mybatisplus.extension.service.IService;
import com.snk.auth.pojo.dto.UserDTO;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-26
 */
public interface PublicUserService extends IService<PublicUser> {

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
