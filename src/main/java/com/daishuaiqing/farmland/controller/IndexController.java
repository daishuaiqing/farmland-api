package com.daishuaiqing.farmland.controller;

import com.daishuaiqing.farmland.domain.Gallery;
import com.daishuaiqing.farmland.query.GalleryQuery;
import com.daishuaiqing.farmland.query.PageParam;
import com.daishuaiqing.farmland.service.IndexService;
import com.daishuaiqing.farmland.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 小程序首页接口
 */
@RestController
public class IndexController {
    @Autowired
    private IndexService indexService;
    /**
     * 首页信息
     * 分类信息
     * @return
     */
    @GetMapping("/wx/home/category")
    public CommonResult indexCategory(){
        return new CommonResult().success(indexService.indexCategory());
    }

    @GetMapping("/wx/home/gallery")
    public CommonResult indexGallery(PageParam page, GalleryQuery galleryQuery){
        Pageable pageable = PageRequest.of(page.getPage(),page.getSize());
        return indexService.indexGallery(pageable,galleryQuery);
    }

}
