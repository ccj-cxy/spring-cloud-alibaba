package com.snk.common.utils;

import cn.hutool.core.util.CharsetUtil;
import lombok.extern.slf4j.Slf4j;
import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import javax.crypto.Cipher;
import java.io.DataOutputStream;
import java.io.File;
import java.io.FileOutputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.security.KeyFactory;
import java.security.KeyPair;
import java.security.KeyPairGenerator;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.security.SecureRandom;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

@Slf4j
public final class RSAUtils {

    /**
     * 加密解密算法
     */
    private static final String ALGORITHM = "RSA";

    /**
     * 1024 bit 生成公钥 私钥
     */
    private static final Integer KEY_SIZE_1024 = 1024;

    /**
     * 2048 bit 生成公钥 私钥
     */
    private static final Integer KEY_SIZE_2048 = 2048;

    /**
     * 默认公钥文件名
     */
    private static final String PUBLIC_KEY_FILE = "publicKey";

    /**
     * 默认私钥文件名
     */
    private static final String PRIVATE_KEY_FILE = "publicKey";


    /**
     * 私有构造器 防止被实例化
     */
    private RSAUtils() {

    }

    /**
     * 默认生成密钥对
     *
     * @date 2020/10/13 3:14 下午
     * @return boolean
     */
    public static boolean generateDefaultKey() {
        return generateKey(PUBLIC_KEY_FILE, PRIVATE_KEY_FILE, KEY_SIZE_1024);
    }

    /**
     * 生成1024位的密钥对
     *
     * @param publicKeyOutput  公钥输出路径
     * @param privateKeyOutput 私钥输出路径
     * @return boolean
     * @date 2020/10/13 3:00 下午
     */
    public static boolean generate1024Key(String publicKeyOutput, String privateKeyOutput) {
        return generateKey(publicKeyOutput, privateKeyOutput, KEY_SIZE_1024);
    }

    /**
     * 生成2048位的密钥对
     *
     * @param publicKeyOutput  公钥输出路径
     * @param privateKeyOutput 私钥输出路径
     * @return boolean
     * @date 2020/10/13 3:00 下午
     */
    public static boolean generate2048Key(String publicKeyOutput, String privateKeyOutput) {
        return generateKey(publicKeyOutput, privateKeyOutput, KEY_SIZE_2048);
    }

    /**
     * 生成自定义位数的密钥对
     *
     * @param publicKeyOutput  公钥输出路径
     * @param privateKeyOutput 私钥输出路径
     * @param keySize          key size
     * @return 生成成功，返回 {@code true}， 否则返回{@code false}.
     */
    public static boolean generateKey(String publicKeyOutput, String privateKeyOutput, int keySize) {
        // rsa require trust random source
        SecureRandom secureRandom = new SecureRandom();
        try {
            final KeyPairGenerator keyGen = KeyPairGenerator.getInstance(ALGORITHM);
            keyGen.initialize(keySize, secureRandom);

            final KeyPair key = keyGen.generateKeyPair();

            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File(publicKeyOutput)))) {
                String publicKey = new BASE64Encoder().encode(key.getPublic().getEncoded());
                dos.write(publicKey.getBytes());

            }

            try (DataOutputStream dos = new DataOutputStream(new FileOutputStream(new File(privateKeyOutput)))) {
                String privateKey = new BASE64Encoder().encode(key.getPrivate().getEncoded());
                dos.write(privateKey.getBytes());
            }

            return true;

        } catch (Exception e) {
            log.error("generateKey failed", e);
        }

        return false;
    }

    /**
     * 公钥加密指定数据
     *
     * @param key  公钥 用于加密数据
     * @param data 带加密数据
     * @return 返回加密字节数组，异常则返回null
     */
    public static byte[] encrypt(PublicKey key, byte[] data) {
        try {

            final Cipher cipher = Cipher.getInstance(ALGORITHM);

            cipher.init(Cipher.ENCRYPT_MODE, key);

            return cipher.doFinal(data);

        } catch (Exception ex) {
            // do nothing
            log.error("encrypt failed", ex);
        }

        return null;

    }

    /**
     * 私钥解密加密后的字节数组
     *
     * @param key           私钥，用于解密数据
     * @param encryptedData 加密后的字节数组
     * @return 解密后的自己数组 出现异常返回null
     */
    public static byte[] decrypt(PrivateKey key, byte[] encryptedData) {

        try {

            final Cipher cipher = Cipher.getInstance(ALGORITHM);

            cipher.init(Cipher.DECRYPT_MODE, key);

            return cipher.doFinal(encryptedData);

        } catch (Exception ex) {
            // do nothing
            log.error("decrypt failed", ex);
        }

        return null;

    }

    /**
     * 通过公钥文件路径获取公钥对象
     *
     * @param publicKeyPath 公钥文件路径
     * @return The {@link PublicKey} object.
     * @throws Exception 错误则抛出异常
     */
    public static PublicKey getPublicKeyByPath(String publicKeyPath) throws Exception {
        byte[] publicKeyBytes = new BASE64Decoder().decodeBuffer(new String(Files.readAllBytes(Paths.get(publicKeyPath))));
        return KeyFactory.getInstance(ALGORITHM).generatePublic(new X509EncodedKeySpec(publicKeyBytes));
    }

    /**
     * 通过私钥文件路径获取私钥对象
     *
     * @param privateKeyPath 私钥文件路径
     * @return The {@link PrivateKey} object.
     * @throws Exception 错误则抛出异常
     */
    public static PrivateKey getPrivateKeyByPath(String privateKeyPath) throws Exception {
        byte[] privateKeyBytes = new BASE64Decoder().decodeBuffer(new String(Files.readAllBytes(Paths.get(privateKeyPath))));
        return KeyFactory.getInstance(ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
    }

    /**
     * 通过公钥base64编码后的字符串获取公钥对象
     *
     * @param encryptedPublicKey 公钥base64编码后的字符串
     * @return The {@link PublicKey} object.
     * @throws Exception 错误则抛出异常
     */
    public static PublicKey getPublicKeyByKey(String encryptedPublicKey) throws Exception {
        byte[] publicKeyBytes = new BASE64Decoder().decodeBuffer(encryptedPublicKey);
        return KeyFactory.getInstance(ALGORITHM).generatePublic(new X509EncodedKeySpec(publicKeyBytes));
    }

    /**
     * 通过私钥base64编码后的字符串获取私钥对象
     *
     * @param encryptedPrivateKey 私钥base64编码后的字符串
     * @return The {@link PrivateKey} object.
     * @throws Exception 错误则抛出异常
     */
    public static PrivateKey getPrivateKeyByKey(String encryptedPrivateKey) throws Exception {
        byte[] privateKeyBytes = new BASE64Decoder().decodeBuffer(encryptedPrivateKey);
        return KeyFactory.getInstance(ALGORITHM).generatePrivate(new PKCS8EncodedKeySpec(privateKeyBytes));
    }


    public static void main(String[] args) throws Exception {
        String publicPath = "./publicKey";
        String privatePath = "./privateKey";
        boolean isSuccess = RSAUtils.generate1024Key(publicPath,  privatePath);
        System.out.println("isSuccess = " + isSuccess);

        PublicKey publicKey = RSAUtils.getPublicKeyByPath(publicPath);
        byte[] encrypt = RSAUtils.encrypt(publicKey, "你好".getBytes());
        System.out.println("encrypt str: " + new BASE64Encoder().encode(encrypt));
        PrivateKey privateKey = RSAUtils.getPrivateKeyByPath(privatePath);
        byte[] decrypt = RSAUtils.decrypt(privateKey, encrypt);
        System.out.println("new String(decrypt, CharsetUtil.UTF_8) = " + new String(decrypt, CharsetUtil.UTF_8));
    }
}
