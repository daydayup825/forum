package com.tensquare.friend.service;

import com.tensquare.friend.dao.FriendDao;
import com.tensquare.friend.dao.NoFriendDao;
import com.tensquare.friend.entity.Friend;
import com.tensquare.friend.entity.NoFriend;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * @author: fanbopeng
 * @Date: 2018/11/22 18:55
 * @Description:
 */
@Service
@Transactional
public class FriendService {
   @Autowired
   private FriendDao friendDao;
   @Autowired
   private NoFriendDao noFriendDao;



    public int addFriend(String userid, String friendid) {
        //先判断数据库中是否有数据,有就是重复添加好友
        Friend friend = friendDao.findByUseridAndAndFriendid(userid, friendid);
        if (friend!=null){

            return 0;
        }

        //直接添加好友, 让好友表中userid和friendid的状态为0
            friend =new Friend();
            friend.setUserid(userid);
            friend.setFriendid(friendid);
            friend.setIslike("0");
            friendDao.save(friend);
        //(已经有一方是好友,就需要改变两边)判断从friendid到userid是否有数据 如果有就把双方状态都改为1
         if (friendDao.findByUseridAndAndFriendid(friendid, userid)!=null){

                friendDao.updateIsLike("1", userid,friendid );
             friendDao.updateIsLike("1", friendid,userid );


         }


        return 1;
    }

    public int addNoFriend(String userid, String friendid) {
        //先判断是否已经是非好友
        NoFriend noFriend = noFriendDao.findByUseridAndAndFriendid(userid, friendid);
        if(noFriend!=null){

            return  0;
        }
        noFriend = new NoFriend();
        noFriend.setUserid(userid);
        noFriend.setFriendid(friendid);
        noFriendDao.save(noFriend);
        return 1;

    }

    public void deleteFriend(String userid, String friendid) {
        //先删除好友表中userid到friendid数据
        friendDao.deleteFriend(userid,friendid);
        //更新friendid到user id 为0
        friendDao.updateIsLike("0", friendid, userid);
        //非好友表中添加数据
        NoFriend noFriend = new NoFriend();
        noFriend.setFriendid(friendid);
        noFriend.setUserid(userid);
        noFriendDao.save(noFriend);
    }
}
