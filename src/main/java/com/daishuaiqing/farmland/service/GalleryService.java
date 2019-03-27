package com.daishuaiqing.farmland.service;

import com.daishuaiqing.farmland.domain.Gallery;
import com.daishuaiqing.farmland.query.GalleryQuery;
import com.daishuaiqing.farmland.vo.CommonResult;
import org.springframework.data.domain.Pageable;

public interface GalleryService {

    CommonResult findById(Long id);

    CommonResult findAll();

    CommonResult add(Gallery gallery);

    CommonResult modify(Gallery gallery);

    CommonResult list(Pageable pageable, GalleryQuery galleryQuery);

    CommonResult deleteById(Long id);

}