package com.daishuaiqing.farmland.service;

import com.daishuaiqing.farmland.domain.Gallery;
import com.daishuaiqing.farmland.dto.GalleryInfo;
import com.daishuaiqing.farmland.query.GalleryQuery;
import com.daishuaiqing.farmland.vo.CommonResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;


public interface GalleryService {

    CommonResult findById(Long id);

    CommonResult findAll();

    CommonResult add(GalleryInfo galleryInfo);

    CommonResult modify(Gallery gallery);

    Page<Gallery> list(Pageable pageable, GalleryQuery galleryQuery);

    CommonResult deleteById(Long id);

}