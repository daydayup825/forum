package com.tensquare.base.controller;

import com.tensquare.base.pojo.Label;
import com.tensquare.base.service.LabelService;
import entity.PageResult;
import entity.Result;
import entity.StatusCode;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.web.bind.annotation.*;

import java.util.List;

/**
 * @author: fanbopeng
 * @Date: 2018/11/17 19:00
 * @Description:
 */
@RestController
@CrossOrigin
@RequestMapping("/label")
public class LabelController {

    @Autowired
    private LabelService service;

    @GetMapping()
    public Result findAll(){

        return new Result(true, StatusCode.OK,"查询成功",service.findAll());

    }

    @GetMapping("/{labelId}")
    public Result findAllById(@PathVariable("labelId") String labelId){

        return new Result(true, StatusCode.OK,"查询成功",service.findAllById(labelId));

    }
    @PostMapping()
    public  Result save(@RequestBody Label label){

        service.save(label);
        return new Result(true, StatusCode.OK,"保存成功");

    }

   @PutMapping("/{labelId}")
    public  Result updateById(@PathVariable String labelId,@RequestBody Label label){
        label.setId(labelId);
        service.update(label);

        return new Result(true, StatusCode.OK,"更新成功");

    }


    @DeleteMapping("/{labelId}")
    public  Result deleteById(@PathVariable String labelId){

            service.delete(labelId);

        return new Result(true, StatusCode.OK,"删除成功");

    }

    @PostMapping("/search")
    public Result findSearch(@RequestBody Label label){
                List<Label>  labelList =  service.findSearch(label);

            return  new Result(true,StatusCode.OK,"查询成功",labelList);


    }

    @PostMapping("/search/{page}/{size}")
    public Result pageQuery(@RequestBody Label label ,@PathVariable int page, @PathVariable int size){
        Page<Label> labelPage =  service.pageQuery(label,page,size);

        return  new Result(true,StatusCode.OK,"查询成功",new PageResult<Label>(labelPage.getTotalElements(),labelPage.getContent()));


    }





}
