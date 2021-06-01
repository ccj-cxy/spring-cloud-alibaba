package com.snk.auth.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.snk.auth.pojo.domain.PublicUser;

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
     * 根据用户名 获取登录用户
     * @author Cai.ChangJun
     * @param username : 用户名
     * @return UserDTO : 登录对象抽象
     * @version 1.0.0
     * @Date 2021/5/26 23:48
     */
    PublicUser getUserByName(String username);
}
