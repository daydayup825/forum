package com.tensqure.config;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.config.server.EnableConfigServer;

/**
 * @author: fanbopeng
 * @Date: 2018/11/23 15:18
 * @Description:
 */
@EnableConfigServer
@SpringBootApplication
public class ConfigApplication {

    public static void main(String[] args) {

        SpringApplication.run(ConfigApplication.class);
    }
}
