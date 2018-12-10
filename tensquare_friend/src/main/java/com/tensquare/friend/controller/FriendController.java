package com.tensquare.friend.controller;

import com.tensquare.friend.client.UserClient;
import com.tensquare.friend.service.FriendService;
import entity.Result;
import entity.StatusCode;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.JwtBuilder;
import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;

/**
 * @author: fanbopeng
 * @Date: 2018/11/22 18:33
 * @Description:
 */
@RestController
@RequestMapping("/friend")
public class FriendController {

    @Autowired
    private FriendService friendService;

    @Autowired
    private HttpServletRequest request;

    @Autowired
    private UserClient userClient;


    /**
     *
     * 功能描述:
     *
     * @param:  添加好友或者添加非好友
     * @return:
     * @auther: fanbopeng
     * @date: 2018/11/22 18:40
     */
 @PutMapping("/like/{friendid}/{type}")
 public Result  addFriend(@PathVariable String friendid,@PathVariable String type){
        //验证是否登录 并且拿到当前登录的用户的id
     Claims claims = (Claims) request.getAttribute("claims_user");
     if (claims==null){
         return    new Result(false, StatusCode.LOGINERROR,"权限不足");

     }

     //判断是添加好友还是添加非好友
     if (!StringUtils.isEmpty(type)){
         String userid = claims.getId();
         if("1".equals(type)){
             //添加好友
          int flag=   friendService.addFriend(userid,friendid);
          if (flag==0){

              return    new Result(false, StatusCode.ERROR,"不能重复添加好友");
          }
          if (flag==1){

                    userClient.uodateFanscountAndfloowcount(userid, friendid, 1);
                 return    new Result(false, StatusCode.ERROR,"添加成功");
             }



         }else if("2".equals(type)){
             //添加非好友
             int flag=   friendService.addNoFriend(userid, friendid);

             if (flag==0){

                 return    new Result(false, StatusCode.ERROR,"不能重复添加非好友");
             }
             if (flag==1){

                 return    new Result(false, StatusCode.ERROR,"添加成功");
             }

         }

             return    new Result(false, StatusCode.ERROR,"参数异常");


     }


     else {
         return new Result(false, StatusCode.ERROR,"参数异常");
     }

 }

 /**
  *
  * 功能描述:
  *删除好友
  * @param:
  * @return:
  * @auther: fanbopeng
  * @date: 2018/11/22 20:42
  */
 @DeleteMapping("/{friendid}")
 public Result  deleteFriend(@PathVariable String friendid){
     //验证是否登录 并且拿到当前登录的用户的id
     Claims claims = (Claims) request.getAttribute("claims_user");
     if (claims==null){
         return    new Result(false, StatusCode.LOGINERROR,"权限不足");

     }
     String userid = claims.getId();
         friendService.deleteFriend(userid,friendid);
         userClient.uodateFanscountAndfloowcount(userid, friendid, -1);


         return new Result(true, StatusCode.OK,"删除成功");


 }

}
