package com.daishuaiqing.farmland.service.impl;

import com.daishuaiqing.farmland.domain.Collection;
import com.daishuaiqing.farmland.dao.CollectionDao;
import com.daishuaiqing.farmland.service.CollectionService;
import com.daishuaiqing.farmland.query.CollectionQuery;
import com.daishuaiqing.farmland.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

@Service
public class CollectionServiceImpl implements CollectionService {

    @Autowired
    private CollectionDao collectionDao;

    /**
    * collection 设置默认值
    * 创建时间，更新时间，是否删除
    * @param collection
    */
    private void setDefaultValue(Collection collection) {
        collection.setCreateTime(LocalDateTime.now());
        collection.setUpdateTime(LocalDateTime.now());
        collection.setDeleted(0);
    }

    @Override
    public CommonResult findById(Long id) {
        return new CommonResult().success(collectionDao.findById(id).orElse(null));
    }

    @Override
    public CommonResult findAll() {
        return new CommonResult().success(collectionDao.findAll());
    }

    @Override
    public CommonResult add(Collection collection) {
        setDefaultValue(collection);
        return new CommonResult().success(collectionDao.save(collection));
    }

    @Override
    public CommonResult modify(Collection collection) {
        Collection data = collectionDao.findById(collection.getId()).orElse(null);
        collection.setCreateTime(data.getCreateTime());
        collection.setDeleted(data.getDeleted());
        collection.setUpdateTime(LocalDateTime.now());
        return new CommonResult().success(collectionDao.save(collection));
    }

    @Override
    public CommonResult list(Pageable pageable,CollectionQuery collectionQuery) {
        Specification<Collection> specification = new Specification<Collection>() {
            @Override
            public Predicate toPredicate(Root<Collection> root, CriteriaQuery<?> query, CriteriaBuilder cb) {
                List<Predicate> predicates = new ArrayList<>();
                /*if(StringUtils.isNotBlank(collectionQuery)){
                    predicates.add(cb.like(root.get("courseName").as(String.class),"%"+collectionQuery.getCourseName()+"%"));
                }
                if(collection!=null){
                    predicates.add(cb.equal(root.get("state").as(Boolean.class),collectionQuery.getState()));
                }*/
                if (predicates.size() == 0) {
                    return null;
                }
                Predicate[] predicateArr = new Predicate[predicates.size()];
                predicateArr = predicates.toArray(predicateArr);
                return cb.and(predicateArr);
            }

        };
         return new CommonResult().success(collectionDao.findAll(specification,pageable));
    }

    @Override
    public CommonResult deleteById(Long id) {
        collectionDao.deleteById(id);
        return new CommonResult().success(!collectionDao.existsById(id));
    }


}