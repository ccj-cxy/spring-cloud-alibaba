package com.snk.common.exception;

import com.snk.common.webRetrun.ErrorCode;
import com.snk.common.webRetrun.Response;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.HttpRequestMethodNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import javax.servlet.http.HttpServletRequest;
import java.util.List;
import java.util.concurrent.atomic.AtomicReference;

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

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public Response exceptionHandler(MethodArgumentNotValidException exception){
        BindingResult result = exception.getBindingResult();
        StringBuilder stringBuilder = new StringBuilder();
        AtomicReference<String> message = new AtomicReference<>("");
        if (result.hasErrors()) {
            List<ObjectError> errors = result.getAllErrors();
            if (errors != null) {
                errors.forEach(p -> {
                    FieldError fieldError = (FieldError) p;
                    message.set(fieldError.getDefaultMessage());
                    log.warn("有问题的请求参数: dto entity [{}],field [{}],message [{}]",fieldError.getObjectName(), fieldError.getField(), fieldError.getDefaultMessage());
                    stringBuilder.append(fieldError.getDefaultMessage());
                });
            }
        }
        return new Response(403, StringUtils.isNotBlank(message.get())?message:exception.getMessage());
    }

}
