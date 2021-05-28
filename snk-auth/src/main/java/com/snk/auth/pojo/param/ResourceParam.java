package com.snk.auth.pojo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import javax.validation.constraints.NotNull;
import java.io.Serializable;

/**
 * <p>
 * 修改资源信息入参
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-05-28
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@AllArgsConstructor
@NoArgsConstructor
@ApiModel("修改资源信息入参")
public class ResourceParam implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */
    @ApiModelProperty("主键id")
    @NotNull(message = "主键id不能为空")
    private Integer id;

    /**
     * 资源名称
     */
    @ApiModelProperty("资源名称")
    private String name;

    /**
     * 资源类型 1:子系统 2:业务模块 3:页面 4:功能项
     */
    @ApiModelProperty("资源类型 1:子系统 2:业务模块 3:功能项 4:对外接口")
    private Integer type;

    /**
     * 资源地址
     */
    @ApiModelProperty("资源地址")
    private String url;

    /**
     * 访问权限
     */
    @ApiModelProperty("访问权限")
    private String permissionName;

    /**
     * 资源图标
     */
    @ApiModelProperty("资源图标")
    private String icon;

    /**
     * 父级资源id
     */
    @ApiModelProperty("父级资源id")
    private Integer parentId;


}
