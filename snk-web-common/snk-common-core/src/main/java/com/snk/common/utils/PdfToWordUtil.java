package com.snk.common.utils;

import com.documents4j.api.DocumentType;
import com.documents4j.api.IConverter;
import com.documents4j.job.LocalConverter;
import lombok.extern.slf4j.Slf4j;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.text.PDFTextStripper;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.Writer;

/**
 * pdf转word工具类
 * @author: Cai.ChangJun
 * @date: 2021/5/31
 */
@Slf4j
public class PdfToWordUtil{

    /**
     　　* word转pdf
     * @param wordFilePath word文件路径
     * @param pdfFilePath  pdf文件路径
     * @return 成功或失败
     */
    public static boolean docxToPdf(String wordFilePath, String pdfFilePath) {
        boolean result = false;

        File inputFile = new File(wordFilePath);
        File outputFile = new File(pdfFilePath);
        try {
            InputStream inputStream = new FileInputStream(inputFile);
            OutputStream outputStream = new FileOutputStream(outputFile);
            IConverter converter = LocalConverter.builder().build();
            converter.convert(inputStream).as(DocumentType.DOCX).to(outputStream).as(DocumentType.PDF).execute();

            outputStream.close();
            result = true;
        } catch (Exception e) {

            e.printStackTrace();
        }

        return result;
    }

    public static void pdfToDocx(String pdfFilePath) {
        try{
            String pdfFile = pdfFilePath;
            PDDocument doc = PDDocument.load(new File(pdfFile));
            int pagenumber = doc.getNumberOfPages();
            pdfFile = pdfFile.substring(0, pdfFile.lastIndexOf("."));
            String fileName = pdfFile + ".doc";
            File file = new File(fileName);
            if (!file.exists()){
                file.createNewFile();
            }
            FileOutputStream fos = new FileOutputStream(fileName);
            Writer writer = new OutputStreamWriter(fos, "UTF-8");
            PDFTextStripper stripper = new PDFTextStripper();
            // 排序
            stripper.setSortByPosition(true);
            // 设置转换的开始页
            stripper.setStartPage(1);
            // 设置转换的结束页
            stripper.setEndPage(pagenumber);
            stripper.writeText(doc, writer);
            writer.close();
            doc.close();
            log.info("pdf转换word成功！");
        }
        catch (IOException e){
            log.error("pdf转换word失败！");
            e.printStackTrace();
        }
    }

    public static void main(String[] args){
        docxToPdf("D:\\Desktop\\学习资料pdf\\蔡昌俊的个人简历.doc","D:\\Desktop\\蔡昌俊的个人简历.pdf");
    }
}