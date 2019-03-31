package com.daishuaiqing.farmland.service;

import com.daishuaiqing.farmland.domain.Category;
import com.daishuaiqing.farmland.query.GalleryQuery;
import com.daishuaiqing.farmland.vo.CommonResult;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface IndexService {

    List<Category> indexCategory();

    CommonResult indexGallery(Pageable pageable, GalleryQuery galleryQuery);
}
