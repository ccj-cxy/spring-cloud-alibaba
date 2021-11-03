package com.snk.xxljob.dto;

import lombok.Data;

import java.util.Date;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 任务组
 * @Date : 2021/11/1
 */
@Data
public class JobGroupDTO {
    private int id;

    private String appname;

    private String title;

    /** 执行器地址类型：0=自动注册、1=手动录入*/
    private int addressType;

    /**执行器地址列表，多地址逗号分隔(手动录入)*/
    private String addressList;

    private Date updateTime;

}
