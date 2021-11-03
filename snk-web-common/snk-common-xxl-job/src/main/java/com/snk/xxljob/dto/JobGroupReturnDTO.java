package com.snk.xxljob.dto;

import lombok.Data;

import java.util.List;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 接口返回接参
 * @Date : 2021/11/1
 */
@Data
public class JobGroupReturnDTO {
    private Integer recordsFiltered;

    private List<JobGroupDTO> data;

    private Integer recordsTotal;
}
