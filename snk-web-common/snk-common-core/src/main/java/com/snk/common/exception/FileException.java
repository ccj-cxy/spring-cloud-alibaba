package com.snk.common.exception;


/**
 * 文件信息异常类
 * 
 * @author ruoyi
 */
public class FileException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    public FileException(String code, Object[] args)
    {
        super("file");
    }

}
