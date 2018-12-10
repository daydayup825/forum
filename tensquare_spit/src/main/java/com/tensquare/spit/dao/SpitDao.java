package com.tensquare.spit.dao;

import com.tensquare.spit.pojo.Spit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;



/**
 * @author: fanbopeng
 * @Date: 2018/11/19 13:19
 * @Description:
 */
public interface SpitDao extends MongoRepository<Spit,String> {


    Page<Spit>  findByParentid(String parentId, Pageable pageable);




}
