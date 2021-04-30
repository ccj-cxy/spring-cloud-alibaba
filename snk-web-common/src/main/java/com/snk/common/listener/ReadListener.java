package com.snk.common.listener;


import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.Listener;
import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.CellExtra;

import java.util.Map;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description :
 * @Date : 2021/4/20
 */
public interface ReadListener<T> extends Listener {

    /**
     * 当任何一个侦听器执行错误报告时，所有侦听器都将接收此方法。 如果在此处引发异常，则整个读取将终止。
     * 这里是处理读取excel异常的
     *
     * @param exception
     * @param context
     * @throws Exception
     */
    void onException(Exception exception, AnalysisContext context) throws Exception;

    /**
     * 读取每行excel表头时会执行此方法
     *
     * @param headMap
     * @param context
     */
    void invokeHead(Map<Integer, CellData> headMap, AnalysisContext context);

    /**
     * 读取每行数据的时候回执行此方法
     *
     * @param data
     *            one row value. Is is same as {@link AnalysisContext#readRowHolder()}
     * @param context
     *            analysis context
     */
    void invoke(T data, AnalysisContext context);

    /**
     * 如果有额外的单元格信息返回就用此方法处理
     *
     * @param extra
     *            extra information
     * @param context
     *            analysis context
     */
    void extra(CellExtra extra, AnalysisContext context);

    /**
     * 在整个excel sheet解析完毕后执行的逻辑。
     *
     * @param context
     */
    void doAfterAllAnalysed(AnalysisContext context);

    /**
     * 用来控制是否读取下一行的策略
     *
     * @param context
     * @return
     */
    boolean hasNext(AnalysisContext context);
}
