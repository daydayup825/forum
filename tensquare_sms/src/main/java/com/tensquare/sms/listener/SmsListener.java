package com.tensquare.sms.listener;

import com.aliyuncs.exceptions.ClientException;
import com.tensquare.sms.utils.SmsUtil;
import org.springframework.amqp.rabbit.annotation.RabbitHandler;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Map;

/**
 * @author: fanbopeng
 * @Date: 2018/11/20 21:11
 * @Description:
 */
@Component
@RabbitListener(queues = "sms")
public class SmsListener {
    @Value("${aliyun.sms.template_code}")
    private String template_code;
    @Value("${aliyun.sms.sign_name}")
    private String sign_name;


    @Autowired
    private SmsUtil smsUtil;

    @RabbitHandler
    public void executeSms(Map<String,String> map){
        String moblie = map.get("mobile");
        String checkcode = map.get("checkcode");
        System.out.println(moblie);
        System.out.println(checkcode);
        try {
            smsUtil.sendSms(moblie, template_code, sign_name, "{\"code\":\""+checkcode+"\"}");
        } catch (ClientException e) {
            e.printStackTrace();
        }


    }



}
