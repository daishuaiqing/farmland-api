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
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.data.domain.Sort;
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
     * 首页分类菜单加载
     * @return
     */
    @Override
    public List<Category> indexCategory() {
        //按照权重由大到小排序
        Sort sort = new Sort(Sort.Direction.DESC, "orderWt");
        CategoryQuery categoryQuery = new CategoryQuery();
        //只查询需要显示的分类
        categoryQuery.setShow(1);
        List<Category> categories = categoryService.findAll(categoryQuery,sort);
        return categories;
    }

    @Override
    public CommonResult indexGallery(Pageable pageable, GalleryQuery galleryQuery) {
        List<Gallery> galleries = galleryService.list(pageable,galleryQuery).getContent();
        for (Gallery gallery: galleries){
            gallery.setUrl(imageUrlPrefix+gallery.getUrl());
        }
        return new CommonResult().success(galleries);
    }
}
