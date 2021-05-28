package com.snk.common.exception;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 登录异常
 * @Date : 2021/5/26
 */
public class LoginException extends RuntimeException{
    protected final String message ;

    public LoginException(String user) {
        this.message = user+":用户名或密码错误";
    }

    public LoginException() {
        this.message = "用户名或密码错误";
    }

    @Override
    public String getMessage()
    {
        return message;
    }
}
