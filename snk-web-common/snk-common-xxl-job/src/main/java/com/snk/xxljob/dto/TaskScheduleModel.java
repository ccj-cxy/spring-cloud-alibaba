package com.snk.xxljob.dto;

import lombok.Getter;
import lombok.Setter;
import lombok.experimental.Accessors;

/**
 * @Classname TaskScheduleModel
 * @Description TODO
 * @Date 2019/7/30 16:14
 * @Created by zhangzhenjun
 */
@Getter
@Setter
@Accessors(chain = true)
public class TaskScheduleModel  {

    /**
     * 所选作业类型:
     * 1  -> 每天
     * 2  -> 每月
     * 3  -> 每周
     */
    Integer jobType;

    /**一周的哪几天*/
    Integer[] dayOfWeeks;

    /**一个月的哪几天*/
    Integer[] dayOfMonths;

    /**秒  */
    Integer second;

    /**分  */
    Integer minute;

    /**时  */
    Integer hour;

}

