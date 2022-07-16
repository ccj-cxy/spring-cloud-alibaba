package com.snk.userprovider.pojo.domain;

import lombok.Data;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description :
 * @Date : 2021/7/4
 */
@Data
public class Course {

    private Long cid;

    private String cname;

    private Long userId;

    private String cstatus;
}
