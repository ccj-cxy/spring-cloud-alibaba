package com.snk.common.utils;

import cn.hutool.extra.qrcode.QrConfig;
import com.google.zxing.qrcode.decoder.ErrorCorrectionLevel;
import lombok.SneakyThrows;
import org.apache.commons.io.IOUtils;
import org.springframework.util.ResourceUtils;

import javax.servlet.http.HttpServletResponse;
import java.io.ByteArrayInputStream;
import java.io.File;
import java.io.InputStream;
import java.net.URL;
import java.net.URLEncoder;

/**
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description : 二维码生成工具
 * @Date : 2021/5/30
 */
public class QrCodeUtil extends cn.hutool.extra.qrcode.QrCodeUtil {

    /**
     * 想response返回二维码图片
     * @author Cai.ChangJun
     * @param context: 输出内容 json格式字符串
     * @param httpServletResponse: 响应体
     * @return:
     * @version 1.0.0
     * @Date 2021/5/31 0:06
     */
    @SneakyThrows
    public static void outputPng(String context, HttpServletResponse response){
        URL resource = com.snk.common.utils.QrCodeUtil.class.getClassLoader().getResource("application-icon.png");
        File file = ResourceUtils.getFile(resource);
        QrConfig config = QrConfig.create()
                //设置高级纠错
                .setErrorCorrection(ErrorCorrectionLevel.H)
                .setImg(file);
        // 设置边距，既二维码和背景之间的边距
        config.setMargin(3);
        // 高纠错级别
        config.setErrorCorrection(ErrorCorrectionLevel.H);
        byte[] bytes = QrCodeUtil.generatePng(context, config);
        response.setContentType("Content-Type");
        response.setCharacterEncoding("UTF-8");
        response.setHeader("Content-Disposition", "attachment;filename=" + URLEncoder.encode("二维码", "UTF-8")+".png");
        InputStream is =new ByteArrayInputStream(bytes);
        IOUtils.copy(is, response.getOutputStream());
        is.close();
    }

}
