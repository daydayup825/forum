package com.customer;

import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * @author: fanbopeng
 * @Date: 2018/11/20 16:00
 * @Description:
 */
@Component
@RabbitListener(queues = "fanbopeng")
public class Customer {

    @RabbitHandler
    public  void getmessage(String msg){

        System.out.println(msg);

    }



}
