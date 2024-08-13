package com.whz.bigevent;

import com.auth0.jwt.JWT;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.algorithms.Algorithm;
import com.auth0.jwt.interfaces.Claim;
import com.auth0.jwt.interfaces.DecodedJWT;
import org.junit.jupiter.api.Test;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * @author whz
 * @description
 * @since 2024/8/13 22:04
 */
public class JwtTest {

    @Test
    public void generateToken() {
        Map<String, Object> claims = new HashMap<>();
        claims.put("id", 1);
        claims.put("username", "张三");
        // 生成jwt代码
        String token = JWT.create()
                .withClaim("user", claims)  // 添加PayLoad
                .withExpiresAt(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 12))  // 添加过期时间
                .sign(Algorithm.HMAC256("whz")); // 指定算法 配置密钥
        System.out.println(token);
    }

    @Test
    public void parseTokenSuccess() {
        // 定义字符串，模拟用户传递过来的token
        String token = "eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9"
                + ".eyJ1c2VyIjp7ImlkIjoxLCJ1c2VybmFtZSI6IuW8oOS4iSJ9LCJleHAiOjE3MjM2MDEzNTl9"
                + ".uwMGIiuOKTtbIp9GNeHjtjF_BaaXeLAc9V1R0DofmX0";
        JWTVerifier jwtVerifier = JWT.require(Algorithm.HMAC256("whz")).build();    // 传入算法 密钥
        DecodedJWT decodedJWT = jwtVerifier.verify(token);  // 验证token 生成解析后的JWT对象
        Map<String, Claim> claims = decodedJWT.getClaims();
        System.out.println(claims.get("user"));
    }
}
