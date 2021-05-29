package com.snk.jwt.utils;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import lombok.extern.slf4j.Slf4j;

import javax.crypto.SecretKey;
import javax.crypto.spec.SecretKeySpec;
import java.util.Base64;
import java.util.Date;

/**
 * JWT工具类
 */
@Slf4j
public class JWTUtil {

    /**有效期为 60 * 60 *1000  一个小时*/
    private Long JWT_TTL = 3600000L;
    /**设置秘钥明文*/
    private String JWT_KEY = "snk";
    private String ALGORITHM = "ASE";

    public JWTUtil(long ttl,String key,String algorithm) {
        this.JWT_TTL = ttl;
        this.JWT_KEY = key;
        this.ALGORITHM = algorithm;
    }

    /**
     * 创建token JWT是由三段信息构成的，将这三段信息文本用.链接一起就构成了Jwt字符串。
     *第一部分我们称它为头部（header),第二部分我们称其为载荷（payload, 类似于飞机上承载的物品)，第三部分是签证（signature).
     * @param id
     * @param payload
     * @param ttlMillis
     * @return
     */
    public String createJWT(String id, String payload, Long ttlMillis) {

        SignatureAlgorithm signatureAlgorithm = SignatureAlgorithm.HS256;
        long nowMillis = System.currentTimeMillis();
        Date now = new Date(nowMillis);
        if (ttlMillis == null) {
            ttlMillis = JWT_TTL;
        }
        long expMillis = nowMillis + ttlMillis;
        Date expDate = new Date(expMillis);
        SecretKey secretKey = generalKey();

        JwtBuilder builder = Jwts.builder()
                //唯一的ID
                .setId(id)
                // JSON数据
                .setSubject(payload)
                // 签发时间
                .setIssuedAt(now)
                //使用HS256对称加密算法签名, 第二个参数为秘钥
                .signWith(signatureAlgorithm, secretKey)
                // 设置过期时间
                .setExpiration(expDate);
        log.info("JWT内容:" + builder.compact());
        return builder.compact();
    }

    /**
     * 生成加密后的秘钥 secretKey
     *
     * @return
     */
    public  SecretKey generalKey() {
        byte[] encodedKey = Base64.getDecoder().decode(JWT_KEY);
        SecretKey key = new SecretKeySpec(encodedKey, 0, encodedKey.length, ALGORITHM);
        return key;
    }

    /**
     * 解析
     *
     * @param jwt
     * @return
     * @throws Exception
     */
    public  Claims parseJWT(String jwt) throws Exception {
        SecretKey secretKey = generalKey();
        return Jwts.parser()
                .setSigningKey(secretKey)
                .parseClaimsJws(jwt)
                .getBody();
    }

    /*public static void main(String[] args) throws Exception {
        JWTUtil.createJWT(UUID.randomUUID().toString(), "username",
                null);
        Claims claims = JWTUtil.parseJWT("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiIyODU4ZGZmMS1kNDY3LTQ2NmItYTU0NC1kOGQxYzQyNDFkNjIiLCJzdWIiOiJ1c2VybmFtZSIsImlhdCI6MTYyMjAxNDM4MiwiZXhwIjoxNjIyMDE3OTgyfQ.mKi1fqzWGdL6ttoalwUoSd9UNztWHDcBL0Hy7YUglW8");
        System.out.println(claims.getExpiration());
    }*/
}
