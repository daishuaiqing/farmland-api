package com.daishuaiqing.farmland.service;

import com.daishuaiqing.farmland.domain.Category;
import com.daishuaiqing.farmland.query.CategoryQuery;
import com.daishuaiqing.farmland.vo.CommonResult;
import org.springframework.data.domain.Pageable;

public interface CategoryService {

    CommonResult findById(Long id);

    CommonResult findAll();

    CommonResult add(Category category);

    CommonResult modify(Category category);

    CommonResult list(Pageable pageable, CategoryQuery categoryQuery);

    CommonResult deleteById(Long id);

}