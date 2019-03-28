package com.daishuaiqing.farmland.service.impl;

import com.daishuaiqing.farmland.domain.Category;
import com.daishuaiqing.farmland.domain.Gallery;
import com.daishuaiqing.farmland.query.CategoryQuery;
import com.daishuaiqing.farmland.query.GalleryQuery;
import com.daishuaiqing.farmland.query.PageParam;
import com.daishuaiqing.farmland.service.CategoryService;
import com.daishuaiqing.farmland.service.GalleryService;
import com.daishuaiqing.farmland.service.IndexService;
import com.daishuaiqing.farmland.vo.CommonResult;
import com.daishuaiqing.farmland.vo.IndexResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Example;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.domain.JpaSort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class IndexServiceImpl implements IndexService {
    @Autowired
    private CategoryService categoryService;
    @Autowired
    private GalleryService galleryService;
    @Value("${web.image.url.prefix}")
    private String imageUrlPrefix;

    /**
     * 小程序首页数据集合
     * @return
     */
    @Override
    public IndexResult index() {
        IndexResult indexResult = new IndexResult();
        //按照权重由大到小排序
        Sort sort = new Sort(Sort.Direction.DESC, "orderWt");
        CategoryQuery categoryQuery = new CategoryQuery();
        //只查询需要显示的分类
        categoryQuery.setShow(1);
        List<Category> categories = categoryService.findAll(categoryQuery,sort);
        if(categories.size()==0){
            indexResult.setCategories(categories);
            return indexResult;
        }else{
            indexResult.setCategories(categories);
            PageParam pageParam = new PageParam();
            Pageable pageable = PageRequest.of(pageParam.getPage(),pageParam.getSize());
            GalleryQuery galleryQuery = new GalleryQuery();
            galleryQuery.setCategoryId(categories.get(0).getId());
            List<Gallery> galleries = galleryService.list(pageable,galleryQuery).getContent();
            for (Gallery gallery: galleries){
                gallery.setUrl(imageUrlPrefix+gallery.getUrl());
            }
            indexResult.setGalleries(galleries);
            return indexResult;
        }
    }
}
