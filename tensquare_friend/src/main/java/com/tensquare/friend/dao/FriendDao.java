package com.tensquare.friend.dao;

import com.tensquare.friend.entity.Friend;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

/**
 * @author: fanbopeng
 * @Date: 2018/11/22 19:05
 * @Description:
 */
public interface FriendDao extends JpaRepository<Friend,String> {

    Friend findByUseridAndAndFriendid(String userid,String friendid);

    @Modifying
    @Query(value = "UPDATE tb_friend SET islike=? WHERE userid=? AND friendid=?",nativeQuery = true)
    void updateIsLike(String islike,String userid,String friendid);

    @Modifying
    @Query(value = "DELETE  FROM  tb_friend WHERE userid=? AND friendid=?",nativeQuery = true)
    void deleteFriend(String userid, String friendid);
}
