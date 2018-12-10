package com.tensquare.search.pojo;

/**
 * @author: fanbopeng
 * @Date: 2018/11/19 20:31
 * @Description:
 */

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;
import org.springframework.data.elasticsearch.annotations.Field;

import java.io.Serializable;

/**
 * 文章实体类
 */
@Document(indexName = "tensquare", type = "article")
@Data
public class Article implements Serializable {
    @Id
    private String id;//ID
    //index  是否索引, 是否分词,是否存储(是否在页面显示)
    // analyzer 按 analyzer进行分词
    // searchAnalyzer 按searchAnalyzer进行搜索
    @Field(index = true
            , analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String title;//标题
    @Field(index = true
            , analyzer = "ik_max_word", searchAnalyzer = "ik_max_word")
    private String content;//文章正文


    private String state;//审核状态


}

