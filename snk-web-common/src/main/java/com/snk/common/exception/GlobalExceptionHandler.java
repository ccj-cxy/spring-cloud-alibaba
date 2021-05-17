package com.snk.common.exception;

import com.snk.common.webRetrun.ErrorCode;
import com.snk.common.webRetrun.Response;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 全局异常捕获
 * @Date : 2021/4/30
 */
@RestControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    /**
     * 请求方式不支持
     */
    @ExceptionHandler({ HttpRequestMethodNotSupportedException.class })
    public Response handleException(HttpRequestMethodNotSupportedException e)
    {
        log.error(e.getMessage(), e);
        return new Response(new ErrorCode("不支持' " + e.getMethod() + "'请求"),e);
    }

    /**
     * 拦截未知的运行时异常
     */
    @ExceptionHandler(RuntimeException.class)
    public Response notFount(RuntimeException e)
    {
        log.error("运行时异常:", e);
        return new Response(new ErrorCode("运行时异常:" + e.getMessage()));
    }

    /**
     * 系统异常
     */
    @ExceptionHandler(Exception.class)
    public Response handleException(Exception e)
    {
        log.error(e.getMessage(), e);
        return new Response(new ErrorCode("服务器错误，请联系管理员"));
    }

    /**
     * 业务异常
     */
    @ExceptionHandler(BusinessException.class)
    public Response businessException(HttpServletRequest request, BusinessException e)
    {
        log.error(e.getMessage(), e);
        return new Response(new ErrorCode(e.getMessage()));
    }

}
