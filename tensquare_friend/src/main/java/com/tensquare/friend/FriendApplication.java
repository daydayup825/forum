package com.tensquare.friend;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.client.discovery.EnableDiscoveryClient;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;
import org.springframework.cloud.openfeign.EnableFeignClients;
import org.springframework.context.annotation.Bean;
import util.IdWorker;
import util.JwtUtil;

/**
 * @author: fanbopeng
 * @Date: 2018/11/22 17:32
 * @Description:
 */
@EnableDiscoveryClient
@EnableFeignClients
@SpringBootApplication
@EnableEurekaClient
public class FriendApplication {

    public static void main(String[] args) {
        SpringApplication.run(FriendApplication.class);

    }

    @Bean
    public IdWorker idWorkker(){
        return new IdWorker(1, 1);
    }
    @Bean
    public JwtUtil jwtUtil(){
        return new util.JwtUtil();
    }

}
