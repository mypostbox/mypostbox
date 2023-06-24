package com.syedu.utils;

/**
 * author:Administrator
 * createTime:2023/6/118:48
 */

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;

import java.security.Key;
import java.util.Date;

public class JwtUtil {

    // JWT密钥，需要设置为一个字符串
    private static final String SECRET_KEY = "com.syedu.utilskkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkkk";

    /**
     * 生成JWT令牌
     * @param subject
     * @param expirationTime
     * @return
     */
    public static String generateToken(String subject, long expirationTime) {
        Date now = new Date();
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
                //Keys.hmacShaKeyFor()方法生成了用于签名的密钥
        return Jwts.builder()//Jwts.builder()方法构建JWT令牌
                .setSubject(subject) //放入标识
                .setIssuedAt(now) //当前时间
                .setExpiration(new Date(now.getTime() + expirationTime)) //有效时间
                .signWith(key, SignatureAlgorithm.HS256) //加密方式
                .compact(); //放回令牌
       // .compact()方法生成JWT令牌字符串并返回。
    }

    /**
     * 验证并解码JWT令牌
     * @param token
     * @return
     */
    public static Claims verifyToken(String token) {
        Key key = Keys.hmacShaKeyFor(SECRET_KEY.getBytes());
        return Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

//    // 生成JWT令牌
//    public static String generateToken(String subject, long expirationTime) {
//        Date now = new Date();
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//
//        return Jwts.builder()
//                .setSubject(subject)
//                .setIssuedAt(now)
//                .setExpiration(new Date(now.getTime() + expirationTime))
//                .signWith(key)
//                .compact();
//    }
//
//    // 验证并解码JWT令牌
//    public static Claims verifyToken(String token) {
//        Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256);
//        return Jwts.parserBuilder()
//                .setSigningKey(key)
//                .build()
//                .parseClaimsJws(token)
//                .getBody();
//    }


}

