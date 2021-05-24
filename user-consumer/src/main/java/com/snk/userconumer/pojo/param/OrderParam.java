package com.snk.userconumer.pojo.param;

import com.snk.common.domain.param.BasePageParam;
import io.swagger.annotations.ApiModel;
import lombok.Data;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 订单查询类
 * @Date : 2021/5/19
 */
@Data
@ApiModel("查询订单信息入参")
public class OrderParam extends BasePageParam {
}
