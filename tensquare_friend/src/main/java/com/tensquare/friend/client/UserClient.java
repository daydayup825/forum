package com.tensquare.friend.client;

import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;

/**
 * @author: fanbopeng
 * @Date: 2018/11/22 20:36
 * @Description:
 */

@FeignClient("tensquare-user")
public interface UserClient {

    @PutMapping(value = "/user/{userid}/{friendid}/{x}")
    public  void uodateFanscountAndfloowcount(@PathVariable("userid") String userid, @PathVariable("friendid") String friendid,@PathVariable("x") int x);
}
