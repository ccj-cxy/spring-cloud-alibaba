package com.snk.gateway.pojo.dto;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 资源表
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
@ApiModel("资源")
public class ResourceDTO implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 主键id
     */

    private Integer id;

    /**
     * 资源名称
     */
    private String name;

    /**
     * 资源类型 1:子系统 2:业务模块 3:页面 4:功能项
     */
    private Integer type;

    /**
     * 资源地址
     */
    private String url;

    /**
     * 访问权限
     */
    private String permissionName;

    /**
     * 资源图标
     */
    private String icon;

    /**
     * 父级资源id
     */
    private Integer parentId;


}
