package com.daishuaiqing.farmland.service;

import com.daishuaiqing.farmland.domain.Collection;
import com.daishuaiqing.farmland.query.CollectionQuery;
import com.daishuaiqing.farmland.vo.CommonResult;
import org.springframework.data.domain.Pageable;

public interface CollectionService {

    CommonResult findById(Long id);

    CommonResult findAll();

    CommonResult add(Collection collection);

    CommonResult modify(Collection collection);

    CommonResult list(Pageable pageable, CollectionQuery collectionQuery);

    CommonResult deleteById(Long id);

}