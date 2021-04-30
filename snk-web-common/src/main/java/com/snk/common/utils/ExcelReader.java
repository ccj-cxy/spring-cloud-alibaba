package com.snk.common.utils;

import com.alibaba.excel.EasyExcel;
import com.snk.common.listener.JdbcEventListener;
import lombok.AllArgsConstructor;
import lombok.Data;

import javax.validation.Validator;
import java.io.InputStream;
import java.util.Collection;
import java.util.function.Consumer;

/**
 * excel读取工具
 *
 * @author felord.cn
 * @since 2021 /4/14 15:10
 */
@AllArgsConstructor
public class ExcelReader {
    private final Validator validator;

    /**
     * Read Excel.
     *
     * @param <T>  the type parameter
     * @param meta the meta
     */
    public <T> void read(Meta<T> meta) {
        ExcelValidator<T> excelValidator = new ExcelValidator<>(validator, meta.headRowNumber);
        JdbcEventListener<T> readListener = new JdbcEventListener<>(excelValidator, meta.consumer);
        EasyExcel.read(meta.excelStream, meta.domain, readListener)
                .headRowNumber(meta.headRowNumber)
                .sheet()
                .doRead();
    }


    /**
     * 解析需要的元数据
     *
     * @param <T> the type parameter
     */
    @Data
    public static class Meta<T> {
        /**
         * excel 文件流
         */
        private InputStream excelStream;
        /**
         * excel头的行号，参考easyexcel的api和你的实际情况
         */
        private Integer headRowNumber;
        /**
         * 对应excel封装的数据类，需要参考easyexcel教程
         */
        private Class<T> domain;
        /**
         * 解析结果的消费函数
         */
        private Consumer<Collection<T>> consumer;
    }

}