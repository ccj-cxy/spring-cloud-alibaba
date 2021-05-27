package com.snk.gateway.pojo;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.ToString;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@ToString
public class Response<T> {

    private int code = HttpStatus.OK.value();

    private String msg = "操作成功";

    private T data;

    public Response(T data) {
        this.data = data;
    }

    public Response(int code, String msg) {
        this.code = code;
        this.msg = msg;
    }

    public Response(int code, T data) {
        this.code = code;
        this.data = data;
    }

    public Response(ErrorCode errorCode) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMessage();
    }

    public Response(ErrorCode errorCode, T data) {
        this.code = errorCode.getCode();
        this.msg = errorCode.getMessage();
        this.data = data;
    }

}

