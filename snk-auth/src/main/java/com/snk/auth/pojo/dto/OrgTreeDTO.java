package com.snk.auth.pojo.dto;

import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import java.util.List;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 组织树
 * @Date : 2021/6/2
 */
@Data
public class OrgTreeDTO {
    /**
     * 机构ID
     */
    @ApiModelProperty("组织id")
    private Integer id;

    /**
     * 机构代号
     */
    @ApiModelProperty("组织编码")
    private String code;

    /**
     * 机构名
     */
    @ApiModelProperty("组织名")
    private String name;

    /**
     * 机构路径全称
     */
    @ApiModelProperty("机构路径全称")
    private String fullName;

    /**
     * 机构简称
     */
    @ApiModelProperty("机构简称")
    private String shortName;

    /**
     * 排序代码
     */
    @ApiModelProperty("排序代码")
    private String sortCode;

    /**
     * 上级机构
     */
    @ApiModelProperty("排序代码")
    private Integer parentId;

    /**
     * 机构级别
     */
    @ApiModelProperty("机构级别")
    private String level;

    /**
     * 机构类型
     */
    @ApiModelProperty("机构类型")
    private String orgType;

    /**
     * 负责人
     */
    @ApiModelProperty("负责人")
    private String leader;

    /**
     * 机构说明
     */
    @ApiModelProperty("机构说明")
    private String remark;





    /**
     * 子节点信息
     */
    @ApiModelProperty("子节点信息")
    private List<OrgTreeDTO> child;
}
