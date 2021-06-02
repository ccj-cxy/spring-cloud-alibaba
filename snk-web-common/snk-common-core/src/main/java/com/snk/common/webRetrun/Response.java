package com.snk.common.webRetrun;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import org.springframework.http.HttpStatus;

@Getter
@AllArgsConstructor
@ApiModel("返回包装")
@NoArgsConstructor
public class Response<T> {
    @ApiModelProperty("系统响应值")
    private int code = HttpStatus.OK.value();
    @ApiModelProperty("消息")
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

