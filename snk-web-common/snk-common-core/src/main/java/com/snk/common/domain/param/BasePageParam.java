package com.snk.common.domain.param;

import com.snk.common.constants.PageConstant;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 分页基础条件
 * @Date : 2021/5/19
 */
@Data
public class BasePageParam {
    /**页码*/
    @NotNull(message = "页码值不能为空")
    @ApiModelProperty("页码")
    private Integer pageNum;
    /**页数*/
    @NotNull(message = "页数值不能为空")
    @ApiModelProperty("页数")
    private Integer pageSize;

    @ApiModelProperty("排序")
    private String sortOrder;

    /**
     * 无参构造器初始化值
     */
    public BasePageParam(){
        this.pageNum = PageConstant.PAGE_DEFAULT_NUMBER;
        this.pageSize = PageConstant.PAGE_DEFAULT_SIZE;
    }

    /**
     * 校验设置合理性参数
     */
    public void setPageNum(Integer pageNum) {
        if(pageNum !=null && pageNum != 0){
            this.pageNum = pageNum;
        }else{
            this.pageNum= PageConstant.PAGE_DEFAULT_NUMBER;
        }
    }

    /**
     *  校验设置合理性参数
     */
    public void setPageSize(Integer pageSize) {
        if(pageSize !=null && pageSize != 0){
            this.pageSize = pageSize;
        }else{
            this.pageSize=PageConstant.PAGE_DEFAULT_SIZE;
        }
    }
}
