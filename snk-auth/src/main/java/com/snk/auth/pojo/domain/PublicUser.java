package com.snk.auth.pojo.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableId;
import java.time.LocalDateTime;
import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.TableField;
import java.io.Serializable;
import java.util.Date;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotEmpty;

/**
 * <p>
 * 用户表
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-26
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("用户表")
@AllArgsConstructor
@NoArgsConstructor
public class PublicUser implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @TableId(value = "id", type = IdType.AUTO)
    @ApiModelProperty("主键id")
    private Integer id;

    /**
     * 用户名
     */
    @ApiModelProperty("用户名")
    @NotEmpty(message = "用户名不能为空")
    private String name;

    /**
     * 密码
     */
    @ApiModelProperty("密码")
    @NotEmpty(message = "密码不能为空")
    private String password;

    /**
     * 昵称
     */
    @ApiModelProperty("昵称")
    private String nikeName;

    /**
     * 手机
     */
    @ApiModelProperty("手机")
    private String mobilePhone;

    /**
     * QQ
     */
    @ApiModelProperty("QQ")
    private String qq;

    /**
     * 邮箱
     */
    @ApiModelProperty("email")
    private String email;

    /**
     * 状态 0:禁用 1:启用
     */
    @ApiModelProperty("状态 0:禁用 1:启用")
    private Boolean enabled;

    /**
     * 0:系统管理员 1:站点管理员 2:普通用户
     */
    @ApiModelProperty("0:系统管理员 1:站点管理员 2:普通用户")
    private Boolean type;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date createTime;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("更新时间")
    private Date updateTime;

    /**
     * 站点id
     */
    @ApiModelProperty("站点id")
    private Integer webId;


}
