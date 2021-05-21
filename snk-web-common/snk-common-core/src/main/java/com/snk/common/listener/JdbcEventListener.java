package com.snk.common.listener;

import cn.hutool.json.JSONUtil;
import com.alibaba.excel.context.AnalysisContext;
import com.alibaba.excel.event.AnalysisEventListener;
import com.snk.common.utils.ExcelValidator;
import org.springframework.util.CollectionUtils;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.function.Consumer;

/**
 * 该类不可被Spring托管
 *
 * @param <T> the type parameter
 * @author felord.cn
 * @since 2021 /4/14 14:19
 */
public class JdbcEventListener<T> extends AnalysisEventListener<T> {
    /**
     * Excel总条数阈值
     */
    private static final Integer MAX_SIZE = 10000;
    /**
     * 校验工具
     */
    private final ExcelValidator<T> excelValidator;
    /**
     * 如果校验通过消费解析得到的excel数据
     */
    private final Consumer<Collection<T>> batchConsumer;
    /**
     * 解析数据的临时存储容器
     */
    private final List<T> list = new ArrayList<>();

    /**
     * Instantiates a new Jdbc event listener.
     *
     * @param excelValidator Excel校验工具
     * @param batchConsumer  Excel解析结果批量消费工具，可实现为写入数据库等消费操作
     */
    public JdbcEventListener(ExcelValidator<T> excelValidator, Consumer<Collection<T>> batchConsumer) {
        this.excelValidator = excelValidator;
        this.batchConsumer = batchConsumer;
    }

    @Override
    public void onException(Exception exception, AnalysisContext context) throws Exception {
        list.clear();
        throw exception;
    }

    @Override
    public void invoke(T data, AnalysisContext context) {
        // 如果没有超过阈值就把解析的excel字段加入集合
        if (list.size() >= MAX_SIZE) {
            throw new RuntimeException("单次上传条数不得超过：" + MAX_SIZE);
        }
        list.add(data);
    }

    @Override
    public void doAfterAllAnalysed(AnalysisContext context) {
        //全部解析完毕后 对集合进行校验并消费
        if (!CollectionUtils.isEmpty(this.list)) {
            List<String> validated = this.excelValidator.validate(this.list);
            if (CollectionUtils.isEmpty(validated)) {

                this.batchConsumer.accept(this.list);
            } else {
                throw new RuntimeException(JSONUtil.toJsonStr(validated));
            }
        }
    }
}