package com.tensquare.user.config;

import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.config.annotation.web.configuration.WebSecurityConfigurerAdapter;

/**
 * @author: fanbopeng
 * @Date: 2018/11/21 11:41
 * @Description:安全配置类
 */

@Configuration
@EnableWebSecurity
public class WebSecurityConfig extends WebSecurityConfigurerAdapter {
    @Override
    protected void configure(HttpSecurity http) throws Exception {
        //authorizeRequests() 全注解实现的配置的开端,表示开始说明需要的权限
        /*
        *  两部分   1,拦截的路径 2.访问需要的权限
        *  antMatchers 拦截的路径   permitAll 全部通行
        *  .anyRequest任何的请求  authenticated 认证后才能访问
        *   .and().csrf().disable();  固定写法 表示使 csrf 拦截失效
        * */
        http
                .authorizeRequests()
                .antMatchers("/**").permitAll()
                .anyRequest().authenticated()
                .and().csrf().disable();
    }
}

