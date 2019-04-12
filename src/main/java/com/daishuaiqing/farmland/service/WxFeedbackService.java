package com.daishuaiqing.farmland.service;

import com.daishuaiqing.farmland.domain.WxFeedback;
import com.daishuaiqing.farmland.query.WxFeedbackQuery;
import com.daishuaiqing.farmland.vo.CommonResult;
import org.springframework.data.domain.Pageable;

public interface WxFeedbackService {

    CommonResult findById(Long id);

    CommonResult findAll();

    CommonResult add(WxFeedback wxFeedback);

    CommonResult modify(WxFeedback wxFeedback);

    CommonResult list(Pageable pageable, WxFeedbackQuery wxFeedbackQuery);

    CommonResult deleteById(Long id);

}