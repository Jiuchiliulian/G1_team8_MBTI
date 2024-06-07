package com.diary.util;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.Date;
import java.util.Map;

@Component
public class JwtUtils {

    /**
     * 生成JWT令牌
     * @param claims JWT第二部分负载 payload 中存储的内容
     * @return
     */

    @Value("${jwt.headname}")
    private String HEDNAME;
    @Value("${jwt.timeout}")
    private String TIMEOUT;

    @PostConstruct
    public void JwtConfig(){
        hedName = HEDNAME;
        timeout = TIMEOUT;
    }
    private static String hedName;
    private static String timeout;

    public static String generateJwt(Map<String, Object> claims) {
        String jwt = Jwts.builder()
                .addClaims(claims)		// 自定义信息（有效载荷）
                .signWith(SignatureAlgorithm.HS256, hedName)		// 签名算法（头部）
                .setExpiration(new Date(System.currentTimeMillis() + Long.parseLong(timeout)))		// 过期时间
                .compact();
        return jwt;
    }

    /**
     * 解析JWT令牌
     * @param jwt JWT令牌
     * @return JWT第二部分负载 payload 中存储的内容
     */
    public static Claims parseJWT(String jwt) {
        Claims claims = Jwts.parser()
                .setSigningKey(hedName)		// 指定签名密钥（必须保证和生成令牌时使用相同的签名密钥）
                .parseClaimsJws(jwt)			// 指定令牌Token
                .getBody();
        return claims;
    }
}
