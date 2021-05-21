package com.snk.file.service;

import org.springframework.web.multipart.MultipartFile;

/**
 * 文件上传接口
 * @author Cai.ChangJun
 * @version 1.0.0
 * @Date 2021/5/21 17:04
 */
public interface UploadFileService
{
    /**
     * 文件上传接口
     * 
     * @param file 上传的文件
     * @return 访问地址
     * @throws Exception
     */
    public String uploadFile(MultipartFile file) throws Exception;
}
