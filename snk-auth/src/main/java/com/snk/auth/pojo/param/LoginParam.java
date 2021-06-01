package com.snk.auth.pojo.param;

import com.snk.auth.pojo.enums.LoginType;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 登录入参
 * @Date : 2021/6/1
 */
@Data
@ApiModel("登录入参")
public class LoginParam {
    @ApiModelProperty("登录类型")
    private LoginType loginType = LoginType.SNK;
    @NotEmpty(message = "用户名不能为空")
    @ApiModelProperty("登录名")
    private String username;
    @ApiModelProperty("登录密码")
    private String password;

}
