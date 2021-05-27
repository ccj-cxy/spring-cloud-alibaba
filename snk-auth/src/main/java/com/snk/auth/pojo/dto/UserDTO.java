package com.snk.auth.pojo.dto;

import com.snk.auth.pojo.domain.PublicUser;
import lombok.Data;

import java.io.Serializable;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 登录用户
 * @Date : 2021/5/26
 */
@Data
public class UserDTO extends PublicUser implements Serializable {
    private static final long serialVersionUID = 1L;

    private String token;

}
