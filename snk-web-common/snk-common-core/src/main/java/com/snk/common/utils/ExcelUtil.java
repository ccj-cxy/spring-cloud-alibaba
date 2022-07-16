package com.snk.common.utils;

import com.alibaba.excel.EasyExcel;
import  com.alibaba.excel.ExcelReader;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.read.builder.ExcelReaderBuilder;
import com.alibaba.excel.read.metadata.ReadSheet;
import com.alibaba.excel.support.ExcelTypeEnum;
import com.alibaba.excel.util.StringUtils;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.snk.common.domain.dto.EasyExcelDTO;
import com.snk.common.listener.ExcelListener;
import lombok.NoArgsConstructor;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import java.io.BufferedInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description :
 * @Date : 2021/4/20
 */
@NoArgsConstructor
public class ExcelUtil {

    public static void exportExcel(EasyExcelDTO easyExcelDTO){
        HttpServletResponse response = easyExcelDTO.getHttpServletResponse();
        setHttpHeader(easyExcelDTO.getHttpServletResponse(),easyExcelDTO.getFileName(),easyExcelDTO.getFileNameType());
        ExcelWriterBuilder writerSheetBuilder =(ExcelWriterBuilder) EasyExcel.write(getOutPutStream(easyExcelDTO.getHttpServletResponse())).head(easyExcelDTO.getClassModel());
        writerSheetBuilder.sheet(StringUtils.isEmpty(easyExcelDTO.getSheetName())?"sheet1":easyExcelDTO.getSheetName()).doWrite(easyExcelDTO.getData());
    }



   public static void exportExcels(List<EasyExcelDTO> easyExcelDTOS,String FileName,
                                   HttpServletResponse httpServletResponse) {
       ExcelWriter excelWriter = EasyExcel
               .write(getOutPutStream(httpServletResponse))
               .autoCloseStream(true).build();
       setHttpHeader(httpServletResponse,FileName,ExcelTypeEnum.XLSX.getValue());
       //将list拆为多sheet
       for (int i = 0; i < easyExcelDTOS.size(); i++) {
           WriteSheet sheet = EasyExcel.writerSheet().head(easyExcelDTOS.get(i).getClassModel())
                   .excludeColumnFiledNames(easyExcelDTOS.get(i).getExcludeColumnFiledNames())
                   .sheetName(StringUtils.isEmpty(easyExcelDTOS.get(i).getSheetName()) ?
                           "sheet" + i : easyExcelDTOS.get(i).getSheetName()).build();
           excelWriter.write(easyExcelDTOS.get(i).getData(),sheet);
       }
       excelWriter.finish();
   }

    private static void  setHttpHeader(HttpServletResponse response, String fileName, String fileNameType){
        try {
            fileName = URLEncoder.encode(fileName,"UTF-8");
            response.setContentType("application/vnd-ms-excel");
            response.setCharacterEncoding("UTF-8");
            if (!StringUtils.isEmpty(fileNameType)&&fileNameType.equalsIgnoreCase("csv")){
                fileNameType = ".csv";
            }else {
                fileNameType = ExcelTypeEnum.XLSX.getValue();
            }
            response.setHeader("Content-disposition","attachment;filename="+fileName+fileNameType);
        } catch (UnsupportedEncodingException e) {
            e.printStackTrace();
        }

    }


    private static OutputStream getOutPutStream(HttpServletResponse response) {
        try {
            return response.getOutputStream();
        } catch (IOException e) {
            e.printStackTrace();
            throw new RuntimeException("获取输出流失败");
        }
    }

    public static ArrayList readExcel(MultipartFile file, Class clazz){
        try {
            InputStream in = new BufferedInputStream(file.getInputStream());
            ExcelListener listener = new ExcelListener();
            ExcelReaderBuilder builder = EasyExcel.read(in,clazz,listener);
            ExcelReader excelReader = builder.build();
            List<ReadSheet> readSheets = excelReader.excelExecutor().sheetList();
            excelReader.read(readSheets);
            return listener.getSuccessData();
        } catch (IOException e) {
            e.printStackTrace();
            return new ArrayList();
        }
    }
}
