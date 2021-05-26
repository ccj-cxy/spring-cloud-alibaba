package com.snk.common.exception;

/**
 * 业务异常
 * @author Cai.ChangJun
 * @version 1.0.0
 * @Date 2021/4/30 20:01
 */
public class BusinessException extends RuntimeException
{
    private static final long serialVersionUID = 1L;

    protected final String message;

    public BusinessException(String message)
    {
        this.message = message;
    }

    public BusinessException(String message, Throwable e)
    {
        super(message, e);
        this.message = message;
    }

    public BusinessException() {
        this.message = "后台服务异常，请联系管理员";
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
