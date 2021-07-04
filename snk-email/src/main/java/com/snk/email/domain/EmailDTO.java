package com.snk.email.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;
import lombok.experimental.Accessors;
import org.springframework.web.multipart.MultipartFile;

import javax.validation.constraints.NotEmpty;
import javax.validation.constraints.NotNull;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 发送邮件实体
 * @Date : 2021/7/4
 */
@Data
@ToString
@EqualsAndHashCode
@Accessors(chain = true)
@ApiModel("发送邮件所需条件")
public class EmailDTO {
    @ApiModelProperty("主题")
    @NotEmpty(message = "邮件主题不能为空")
    private String subject;

    @ApiModelProperty("邮件正文,使用模板时该字段可以不传")
    private String content;

    @ApiModelProperty("邮件发送人，数组可以传多个")
    @NotNull(message = "发送人不能为空")
    private String[] to;

    /**抄送*/
    @ApiModelProperty("抄送")
    private String[] cc;

    /**密送*/
    @ApiModelProperty("密送")
    private String[] bcc;

    @ApiModelProperty("邮件附件")
    private MultipartFile[] multipartFiles;
}
