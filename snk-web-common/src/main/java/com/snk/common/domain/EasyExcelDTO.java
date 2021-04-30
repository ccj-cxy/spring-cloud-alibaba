package com.snk.common.domain;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;
import lombok.ToString;

import javax.servlet.http.HttpServletResponse;
import java.util.List;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description :
 * @Date : 2021/4/30
 */
@Data
@NoArgsConstructor
@EqualsAndHashCode
@ToString
public class EasyExcelDTO<T> {
    /**是否需要表头*/
    private Boolean isNeedHead = true;
    /**模型*/
    private Class classModel;
    /**sheet名称*/
    private String sheetName;
    /**文件名称*/
    private String fileName;
    /**响应*/
    private HttpServletResponse httpServletResponse;
    /**数据*/
    private List<T> data;
    /**文件类型*/
    private String fileNameType;
    protected boolean canEqual(final Object other) {
        return other instanceof EasyExcelDTO;
    }
}
