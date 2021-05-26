package com.snk.common.utils;

import java.util.Base64;

/**
 * base64对称加密算法
 * @author :Cai.ChangJun
 * @version 1.0.0
 * @Description :
 * @Date : 2021/5/26
 */
public class Base64Util {

    /**
     * 解密
     * @param data 加密数据
     * @return
     */
    public static String base64Decoder(String data) {
        try {
            byte[] orgAndUser = data.getBytes();
            // BASE64解密
            Base64.Decoder decoder = Base64.getDecoder();
            byte[] bytes = decoder.decode(orgAndUser);
            return new String(bytes);
        }catch (Exception e) {
            System.out.println("BASE64解密异常");
            e.printStackTrace();
        }
        return null;
    }

    /**
     * 加密
     * @return
     */
    public static String base64Encoder(String encoderData) {
        try {
            Base64.Encoder encoder = Base64.getEncoder();
            byte[] data = encoder.encode(encoderData.getBytes());
            return new String(data);
        } catch (Exception e) {
            System.out.println("BASE64加密异常");
            e.printStackTrace();
        }
        return null;
    }
}
