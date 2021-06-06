package com.snk.auth.pojo.domain;

import com.baomidou.mybatisplus.annotation.FieldFill;
import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.time.LocalDateTime;
import java.util.Date;

/**
 * <p>
 * 部门机构 
 * </p>
 *
 * @author Cai.ChangJun
 * @since 2021-06-06
 */
@Data
@EqualsAndHashCode(callSuper = false)
@Accessors(chain = true)
@ApiModel("组织表")
public class PublicOrg implements Serializable {

    private static final long serialVersionUID = 1L;

    /**
     * 机构ID
     */
    @TableId(value = "id", type = IdType.AUTO)
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
     * 创建人
     */
    @ApiModelProperty("创建人")
    private String createdBy;

    /**
     * 创建时间
     */
    @TableField(fill = FieldFill.INSERT)
    @ApiModelProperty("创建时间")
    private Date createdTime;

    /**
     * 更新人
     */
    @ApiModelProperty("更新人")
    private String updatedBy;

    /**
     * 更新时间
     */
    @TableField(fill = FieldFill.INSERT_UPDATE)
    @ApiModelProperty("更新时间")
    private LocalDateTime updatedTime;


}
