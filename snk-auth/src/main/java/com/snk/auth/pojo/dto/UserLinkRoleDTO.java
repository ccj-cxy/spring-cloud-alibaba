package com.snk.auth.pojo.dto;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;

import javax.validation.constraints.NotNull;
import java.util.Set;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 用户关联角色
 * @Date : 2021/5/27
 */
@Data
@EqualsAndHashCode(callSuper = false)
@ApiModel("用户关联角色")
public class UserLinkRoleDTO {
    @ApiModelProperty("用户id")
    @NotNull(message = "用户id不能为空")
    private Integer      userId;
    @ApiModelProperty("角色id集合")
    @NotNull(message = "角色集合不能为空")
    private Set<Integer> roleIds;
}
