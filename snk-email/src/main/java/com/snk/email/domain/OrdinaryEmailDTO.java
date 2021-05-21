package com.snk.email.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

import java.util.Date;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 普通邮件
 * @Date : 2021/5/5
 */
@Data
@ApiModel("普通邮件")
public class OrdinaryEmailDTO {
    /**邮件id*/
    @ApiModelProperty("邮件id")
    private String id;
    /**邮件发送人*/
    @ApiModelProperty("邮件发送人")
    private String from;
    /**邮件接收人*/
    @ApiModelProperty("邮件接收人")
    private String to;
    /**邮件主题*/
    @ApiModelProperty("邮件主题")
    private String subject;
    /**邮件内容*/
    @ApiModelProperty("邮件内容")
    private String text;
    /**发送时间*/
    @ApiModelProperty("发送时间")
    private Date sentDate;
    /**抄送*/
    @ApiModelProperty("抄送")
    private String cc;
    /**密送*/
    @ApiModelProperty("密送")
    private String bcc;
    /**状态*/
    @ApiModelProperty("状态")
    private String status;
    /**报错信息*/
    @ApiModelProperty("报错信息")
    private String error;
    @JsonIgnore
    /**邮件附件*/
    @ApiModelProperty("邮件附件")
    private MultipartFile[] multipartFiles;
}
