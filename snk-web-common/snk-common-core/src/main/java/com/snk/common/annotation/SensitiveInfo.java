package com.snk.common.annotation;

import com.snk.common.constants.enums.SensitiveTypeEnum;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 数据脱敏注解
 * @Date : 2021/7/19
 */
public @interface SensitiveInfo {
    SensitiveTypeEnum value() ;
}
