package com.tensquare.jwt;

import io.jsonwebtoken.JwtBuilder;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import javafx.beans.binding.SetExpression;

import java.util.Date;

/**
 * @author: fanbopeng
 * @Date: 2018/11/21 16:51
 * @Description:
 */
public class CreateJwt {

    public static void main(String[] args) {
    //指定 载荷
    JwtBuilder jwtBuilder=        Jwts.builder()
                    .setId("666")
                    .setSubject("小范带你学java")   //指定主体
                     .setIssuedAt(new Date())
                    .signWith(SignatureAlgorithm.HS256, "xiaofan")//指定签名算法和盐
                    .setExpiration((new Date(new Date().getTime()+6000)))        //设置token的过期时间
                    .claim("role", "admin"); //设置用户角色
        System.out.println(jwtBuilder.compact());
    }

}
