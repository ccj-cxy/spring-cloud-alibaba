package com.snk.file.pojo.domain;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;
import org.springframework.web.multipart.MultipartFile;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description :
 * @Date : 2021/5/22
 */
@ApiModel(value = "上传文件实体")
@Data
public class UploadDTO {
    @ApiModelProperty("文件")
    private MultipartFile file;
    @ApiModelProperty("桶名")
    private String bucketName;
}
