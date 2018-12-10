package com.tensquare.sms;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

/**
 * @author: fanbopeng
 * @Date: 2018/11/20 21:10
 * @Description:
 */
@SpringBootApplication
@EnableEurekaClient
public class SmsApplication {


    public static void main(String[] args) {
        SpringApplication.run(SmsApplication.class);

    }

}
