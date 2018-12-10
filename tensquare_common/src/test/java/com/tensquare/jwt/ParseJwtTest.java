package com.tensquare.jwt;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtParser;
import io.jsonwebtoken.Jwts;

import java.text.SimpleDateFormat;

/**
 * @author: fanbopeng
 * @Date: 2018/11/21 16:59
 * @Description:
 */
public class ParseJwtTest {
    public static void main(String[] args) {

        Claims claims = Jwts.parser()
                .setSigningKey("xiaofan").parseClaimsJws
                        ("eyJhbGciOiJIUzI1NiJ9.eyJqdGkiOiI2NjYiLCJzdWIiOiLlsI_" +
                                "ojIPluKbkvaDlraZqYXZhIiwiaWF0IjoxNTQyNzkwNjY3fQ.q0gLvndpoY" +
                                "LozYzcKJ9cncoeY6T-BJju2T8j_Pfycew")
                .getBody();
        System.out.println("id:"+claims.getId());
        System.out.println("名:"+claims.getSubject());
        System.out.println("登录时间:"+new SimpleDateFormat().format(claims.getIssuedAt()));
        System.out.println("过期时间:"+new SimpleDateFormat().format(claims.getExpiration()));
        System.out.println("名:"+claims.get("role"));
    }
}
