package com.tensquare.spit;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.jdbc.DataSourceAutoConfiguration;
import org.springframework.context.annotation.Bean;
import util.IdWorker;

/**
 * @author: fanbopeng
 * @Date: 2018/11/19 11:56
 * @Description:
 */
@SpringBootApplication
@EnableAutoConfiguration(exclude={DataSourceAutoConfiguration.class})
public class SpitApplication {


    public static void main(String[] args) {

        SpringApplication.run(SpitApplication.class);

    }

    @Bean
    public IdWorker idWorker(){

        return new IdWorker();
    }

}
