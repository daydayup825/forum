package com.tensquare.friend.dao;

import com.tensquare.friend.entity.Friend;
import com.tensquare.friend.entity.NoFriend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author: fanbopeng
 * @Date: 2018/11/22 19:05
 * @Description:
 */
public interface NoFriendDao extends JpaRepository<NoFriend,String> {

    NoFriend findByUseridAndAndFriendid(String userid, String friendid);


}
