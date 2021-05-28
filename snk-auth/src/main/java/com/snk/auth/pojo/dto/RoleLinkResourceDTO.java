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
@ApiModel("角色关联资源")
public class RoleLinkResourceDTO {
    @ApiModelProperty("角色id")
    @NotNull(message = "角色id不能为空")
    private Integer      roleId;
    @ApiModelProperty("资源id集合")
    @NotNull(message = "资源集合不能为空")
    private Set<Integer> resourceIds;
}
