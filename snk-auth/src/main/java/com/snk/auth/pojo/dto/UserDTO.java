package com.snk.auth.pojo.dto;

import com.snk.auth.pojo.domain.PublicResource;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotEmpty;
import java.io.Serializable;
import java.util.Set;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 登录用户
 * @Date : 2021/5/26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("登录用户信息")
public class UserDTO implements Serializable {
    private static final long serialVersionUID = 1L;

    @ApiModelProperty("主键id")
    private Integer       id;

    @ApiModelProperty("用户名")
    @NotEmpty(message = "用户名不能为空")
    private String        name;

    @ApiModelProperty("昵称")
    private String        nikeName;

    @ApiModelProperty("手机")
    private String       mobilePhone;

    @ApiModelProperty("QQ")
    private String       qq;

    @ApiModelProperty("email")
    private String       email;

    @ApiModelProperty("状态 0:禁用 1:启用")
    private Integer      enabled;

    @ApiModelProperty("0:系统管理员 1:站点管理员 2:普通用户")
    private Integer      type;

    @ApiModelProperty("站点id")
    private Integer      webId;

    @ApiModelProperty("登录令牌")
    private String       token;

    @ApiModelProperty("用户拥有的角色id集合")
    private Set<Integer> roleIds;

    @ApiModelProperty("用户拥有的资源集合")
    private Set<PublicResource> resources;

}
