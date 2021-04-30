package com.snk.common.listener;

import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description :
 * @Date : 2021/4/20
 */
@Slf4j
@NoArgsConstructor
@Data
public class ExcelListener<T> extends AnalysisEventListener<T> {
    private ArrayList<T> successData = new ArrayList<>();
    private ArrayList<T> errorData = new ArrayList<>();
    @Override
    public void invoke(T t, AnalysisContext analysisContext) {
        this.successData.add(t);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext analysisContext) {

    }
}
