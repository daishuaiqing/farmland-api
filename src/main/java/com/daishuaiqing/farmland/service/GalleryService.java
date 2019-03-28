package com.daishuaiqing.farmland.service;

import com.daishuaiqing.farmland.domain.Gallery;
import com.daishuaiqing.farmland.query.GalleryQuery;
import com.daishuaiqing.farmland.vo.CommonResult;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface GalleryService {

    CommonResult findById(Long id);

    CommonResult findAll();

    CommonResult add(Gallery gallery);

    CommonResult modify(Gallery gallery);

    Page<Gallery> list(Pageable pageable, GalleryQuery galleryQuery);

    CommonResult deleteById(Long id);

}