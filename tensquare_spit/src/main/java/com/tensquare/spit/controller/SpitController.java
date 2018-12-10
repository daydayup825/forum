package com.tensquare.spit.controller;

import com.tensquare.spit.pojo.Spit;
import com.tensquare.spit.service.SpitService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.web.bind.annotation.*;

import java.util.Map;

/**
 * @author: fanbopeng
 * @Date: 2018/11/19 13:28
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("/spit")
public class SpitController {

    @Autowired
    private SpitService spitService;

    @Autowired
    private RedisTemplate redisTemplate;


    @GetMapping
    public Result findAll(){


        return new Result(true, StatusCode.OK,"查询成功",spitService.findAll());

    }

    @GetMapping("/{spitId}")
    public Result findById(@PathVariable String spitId ){


        return new Result(true, StatusCode.OK,"查询成功",spitService.findById(spitId));

    }

    @PostMapping("/{spitId}")
    public Result save(@RequestBody Spit spit){


        spitService.save(spit);
        return new Result(true, StatusCode.OK,"保存成功");

    }


    @PutMapping("/{spitId}")
    public Result update(@PathVariable String spitId,     @RequestBody Spit spit){
            spit.set_id(spitId);
        spitService.update(spit);
        return new Result(true, StatusCode.OK,"更新成功");

    }

    @DeleteMapping("/{spitId}")
    public Result deleteById(@PathVariable String spitId){

        spitService.deleteById(spitId);
        return new Result(true, StatusCode.OK,"删除成功");

    }

    @GetMapping("/commont/{parentId}/{page}/{size}")
    public Result findByParentId(@PathVariable String parentId, @PathVariable int page, @PathVariable int size){
        Page<Spit> spits = spitService.findByparentId(parentId, page, size);

        return new Result(true, StatusCode.OK,"删除成功",new PageResult<Spit>(spits.getTotalPages(),spits.getContent()));

    }


    @PutMapping("/thumbup/{spitId}")
    public Result thumbup(@PathVariable String spitId){
        //点赞前先判断用户是否已经点赞

        String userid="111";
        Object thumbup = redisTemplate.opsForValue().get("thumbup_" + userid);
        if (thumbup!=null){

            return new Result(true, StatusCode.REPERROR,"您已经点赞了");
        }

        spitService.thumbup(spitId);
        redisTemplate.opsForValue().set("thumbup_"+userid, 1);
        return new Result(true, StatusCode.OK,"点赞成功");

    }

}
