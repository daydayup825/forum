package com.tensquare.qa.client.impl;

import com.tensquare.qa.client.BaseClient;
import entity.Result;
import entity.StatusCode;
import org.springframework.stereotype.Component;

/**
 * @author: fanbopeng
 * @Date: 2018/11/23 10:35
 * @Description:
 */
@Component
public class BaseClientImpl  implements BaseClient{
    @Override
    public Result findAllById(String labelId) {


        return new Result(false, StatusCode.ERROR,"熔断器触发了");
    }
}
