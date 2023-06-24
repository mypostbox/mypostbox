package com.syedu.utils.keyword;

import com.syedu.domain.Users;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jws;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.joda.time.DateTime;

import java.security.PrivateKey;
import java.security.PublicKey;


public class JwtUtils {
    /**
     * 私钥加密token 这个令牌被用 RSA 非对称加密算法 的私钥加密过
     *  得到一个令牌
     * @param manager      载荷中的数据
     * @param privateKey    私钥
     * @param expireMinutes 过期时间，单位 分钟   7 * 24 * 60   7天之后过期
     * @return
     * @throws Exception
     */
    public static String generateToken(Users  manager, PrivateKey privateKey, int expireMinutes) throws Exception {

        return Jwts.builder()
                // 载荷的部分
                .claim("id", manager.getId())
                .claim("username", manager.getUsername())
                .claim("password", manager.getPassword())
                .claim("email",manager.getEmail())
                .setExpiration(DateTime.now().plusMinutes(expireMinutes).toDate())  //Date类型的对象
                .signWith(SignatureAlgorithm.RS256, privateKey)    //使用哪种算法做签名  私钥签名
                .compact();
    }

    /**
     * 私钥加密token
     *
     * @param manager     载荷中的数据
     * @param privateKey    私钥字节数组
     * @param expireMinutes 过期时间，单位秒
     * @return
     * @throws Exception
     */
    public static String generateToken(Users manager, byte[] privateKey, int expireMinutes) throws Exception {
        return Jwts.builder()
                .claim("id", manager.getId())
                .claim("username", manager.getUsername())
                .setExpiration(DateTime.now().plusMinutes(expireMinutes).toDate())
                .signWith(SignatureAlgorithm.RS256, RsaUtils.getPrivateKey(privateKey))
                .compact();
    }

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token 获取信息，载荷
     * @param publicKey 公钥 需要一个公钥  相当于解密的过程
     * @return
     * @throws Exception
     */
    private static Jws<Claims> parserToken(String token, PublicKey publicKey) {
        return Jwts.parser().setSigningKey(publicKey).parseClaimsJws(token);
    }

    /**
     * 公钥解析token
     *
     * @param token     用户请求中的token
     * @param publicKey 公钥字节数组
     * @return
     * @throws Exception
     */
    private static Jws<Claims> parserToken(String token, byte[] publicKey) throws Exception {
        return Jwts.parser().setSigningKey(RsaUtils.getPublicKey(publicKey))
                .parseClaimsJws(token);
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     * @throws Exception
     */
    public static Users getInfoFromToken(String token, PublicKey publicKey) throws Exception {
        // 根据公钥去拿载荷
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        Object id = body.get("id");
        Object username = body.get("username");
        return new Users().setId(Integer.parseInt(id.toString())).setUsername(username.toString());
    }

    /**
     * 获取token中的用户信息
     *
     * @param token     用户请求中的令牌
     * @param publicKey 公钥
     * @return 用户信息
     * @throws Exception
     */
    public static Users getInfoFromToken(String token, byte[] publicKey) throws Exception {
        Jws<Claims> claimsJws = parserToken(token, publicKey);
        Claims body = claimsJws.getBody();
        Object id = body.get("id");
        Object username = body.get("username");
        return new Users().setId(Integer.parseInt(id.toString())).setUsername(username.toString());
    }
}
