package com.snk.auth.pojo.param;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.io.Serializable;

/**
 * <p>
 * 修改组织信息入参
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
@ApiModel("修改组织信息入参")
public class OrgParam implements Serializable {

    private static final long serialVersionUID = 1L;

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


}
