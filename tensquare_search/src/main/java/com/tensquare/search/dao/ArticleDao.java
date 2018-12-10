package com.tensquare.search.dao;

import com.tensquare.search.pojo.Article;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

/**
 * @author: fanbopeng
 * @Date: 2018/11/19 20:44
 * @Description:
 */
public interface ArticleDao extends ElasticsearchRepository<Article,String> {

    Page<Article>  findByTitleOrContentLike(String title, String content, Pageable pageable);

}
