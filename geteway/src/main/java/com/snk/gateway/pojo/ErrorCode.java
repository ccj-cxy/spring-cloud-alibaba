package com.snk.gateway.pojo;

import lombok.Data;

/**
 * 错误码
 * @author Cai.ChangJun
 * @version 1.0.0
 * @Date 2021/5/17 23:33
 */
@Data
public class ErrorCode {
    private int code = 500;
    private String message = "后台服务异常,请联系管理员";
    public ErrorCode(String msg) {
        this.message = msg;
    }
}