package com.snk.auth.pojo.dto;

import lombok.Data;

import java.util.List;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 资源树
 * @Date : 2021/6/2
 */
@Data
public class ResourceTreeDTO {
    /**
     * 资源id
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

    /**
     * 子节点信息
     */
    private List<ResourceTreeDTO> child;
}
