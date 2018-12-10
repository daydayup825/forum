package com.tensquare.base.service;

import com.tensquare.base.dao.LabelDao;
import com.tensquare.base.pojo.Label;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.CollectionUtils;
import util.IdWorker;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

/**
 * @author: fanbopeng
 * @Date: 2018/11/17 19:24
 * @Description:
 */
@Service
public class LabelService {

     @Autowired
    private LabelDao labelDao;

     @Autowired
     private IdWorker idWorker;



     public List<Label> findAll(){

         List<Label> labels = labelDao.findAll();

         return labels;

     }

    public Label findAllById(String id){

         return labelDao.findById(id).get();

    }
    @Transactional
    public  void save(Label label){

            label.setId(idWorker.nextId()+"");
            labelDao.save(label);
    }

    public  void update(Label  label){

         labelDao.save(label);
    }

    public  void delete(String   id){

        labelDao.deleteById(id);
    }


    public List<Label> findSearch(Label label) {

         return labelDao.findAll(new Specification<Label>() {
             // root 跟对象
             // query 封装的都是查询条件 比如 group by order by
             // criteriaBuilder 用来封装条件对象

             @Override
             public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                  List<Predicate> list = new ArrayList<>();


                if (label.getLabelname()!=""&&"".equals(label.getLabelname())) {
                    Predicate predicate = criteriaBuilder.like(root.get("labelName").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }

                 if (label.getLabelname()!=""&&"".equals(label.getLabelname())) {
                     Predicate predicate = criteriaBuilder.like(root.get("status").as(String.class),   label.getState());
                     list.add(predicate);
                 }

                 Predicate[] predicates = new Predicate[list.size()];
                 predicates = list.toArray(predicates);
                 return criteriaBuilder.and(predicates);
             }
         });




    }

    public Page<Label> pageQuery(Label label, int page, int size) {

         //封装page对象
     Pageable pageable =PageRequest.of(page-1, size);


        return labelDao.findAll(new Specification<Label>() {
            // root 跟对象
            // query 封装的都是查询条件 比如 group by order by
            // criteriaBuilder 用来封装条件对象

            @Override
            public Predicate toPredicate(Root<Label> root, CriteriaQuery<?> query, CriteriaBuilder criteriaBuilder) {
                List<Predicate> list = new ArrayList<>();


                if (label.getLabelname()!=""&&"".equals(label.getLabelname())) {
                    Predicate predicate = criteriaBuilder.like(root.get("labelName").as(String.class), "%" + label.getLabelname() + "%");
                    list.add(predicate);
                }

                if (label.getLabelname()!=""&&"".equals(label.getLabelname())) {
                    Predicate predicate = criteriaBuilder.like(root.get("status").as(String.class),   label.getState());
                    list.add(predicate);
                }

                Predicate[] predicates = new Predicate[list.size()];
                predicates = list.toArray(predicates);
                return criteriaBuilder.and(predicates);
            }
        }, pageable);




    }
}
