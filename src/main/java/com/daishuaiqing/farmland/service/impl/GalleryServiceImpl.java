package com.daishuaiqing.farmland.service.impl;

import com.daishuaiqing.farmland.domain.Gallery;
import com.daishuaiqing.farmland.dao.GalleryDao;
import com.daishuaiqing.farmland.service.GalleryService;
import com.daishuaiqing.farmland.query.GalleryQuery;
import com.daishuaiqing.farmland.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.Page;
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
public class GalleryServiceImpl implements GalleryService {

    @Autowired
    private GalleryDao galleryDao;

    /**
    * gallery 设置默认值
    * 创建时间，更新时间，是否删除
    * @param gallery
    */
    private void setDefaultValue(Gallery gallery) {
        gallery.setCreateTime(LocalDateTime.now());
        gallery.setUpdateTime(LocalDateTime.now());
        gallery.setDeleted(0);
    }

    @Override
    public CommonResult findById(Long id) {
        return new CommonResult().success(galleryDao.findById(id).orElse(null));
    }

    @Override
    public CommonResult findAll() {
        return new CommonResult().success(galleryDao.findAll());
    }

    @Override
    public CommonResult add(Gallery gallery) {
        setDefaultValue(gallery);
        return new CommonResult().success(galleryDao.save(gallery));
    }

    @Override
    public CommonResult modify(Gallery gallery) {
        Gallery data = galleryDao.findById(gallery.getId()).orElse(null);
        gallery.setCreateTime(data.getCreateTime());
        gallery.setDeleted(data.getDeleted());
        gallery.setUpdateTime(LocalDateTime.now());
        return new CommonResult().success(galleryDao.save(gallery));
    }

    @Override
    public Page<Gallery> list(Pageable pageable, GalleryQuery galleryQuery) {
        Gallery gallery = new Gallery();
        if(galleryQuery.getCategoryId()!=null){
            gallery.setCategoryId(galleryQuery.getCategoryId());
        }
        Example<Gallery> example = Example.of(gallery);
        return galleryDao.findAll(example,pageable);
    }

    @Override
    public CommonResult deleteById(Long id) {
        galleryDao.deleteById(id);
        return new CommonResult().success(!galleryDao.existsById(id));
    }


}