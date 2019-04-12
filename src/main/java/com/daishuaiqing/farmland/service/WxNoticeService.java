package com.daishuaiqing.farmland.service;

import com.daishuaiqing.farmland.domain.WxNotice;
import com.daishuaiqing.farmland.query.WxNoticeQuery;
import com.daishuaiqing.farmland.vo.CommonResult;
import org.springframework.data.domain.Pageable;

public interface WxNoticeService {

    CommonResult findById(Long id);

    CommonResult findAll();

    CommonResult add(WxNotice wxNotice);

    CommonResult modify(WxNotice wxNotice);

    CommonResult list(Pageable pageable, WxNoticeQuery wxNoticeQuery);

    CommonResult deleteById(Long id);

}