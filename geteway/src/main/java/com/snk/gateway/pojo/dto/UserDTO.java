package com.snk.gateway.pojo.dto;

import lombok.Builder;
import lombok.Data;

import java.util.Date;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 登录用户
 * @Date : 2021/5/26
 */
@Data
@Builder
public class UserDTO {
    private Integer id;
    /**
     * 用户名
     */
    private String name;

    /**
     * 密码
     */
    private String password;

    /**
     * 昵称
     */
    private String nikeName;

    /**
     * 手机
     */
    private String mobilePhone;

    /**
     * QQ
     */
    private String qq;

    /**
     * 邮箱
     */
    private String email;

    /**
     * 状态 0:禁用 1:启用
     */
    private Boolean enabled;

    /**
     * 0:系统管理员 1:站点管理员 2:普通用户
     */
    private Boolean type;

    /**
     * 创建时间
     */
    private Date createTime;

    /**
     * 更新时间
     */
    private Date updateTime;

    /**
     * 站点id
     */
    private Integer webId;

    /**
     * 令牌
     */
    private String token;
}
