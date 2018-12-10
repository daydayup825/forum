package com.tensquare.friend.entity;

import lombok.Data;


import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.IdClass;
import javax.persistence.Table;
import java.io.Serializable;

/**
 * @author: fanbopeng
 * @Date: 2018/11/22 17:35
 * @Description:
 */
@Entity
@Table(name="tb_friend")
@IdClass(Friend.class)
@Data
public class Friend implements Serializable {
    @Id
    private String userid;
    @Id
    private String friendid;

    private String islike;
    }