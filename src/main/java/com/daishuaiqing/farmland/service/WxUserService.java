package com.daishuaiqing.farmland.service;

import com.daishuaiqing.farmland.domain.WxUser;
import com.daishuaiqing.farmland.dto.WxLoginInfo;
import com.daishuaiqing.farmland.query.WxUserQuery;
import com.daishuaiqing.farmland.vo.CommonResult;
import com.daishuaiqing.farmland.vo.WxUserResult;
import org.springframework.data.domain.Pageable;

public interface WxUserService {

    CommonResult findById(Long id);

    CommonResult findAll();

    CommonResult add(WxUser wxUser);

    CommonResult modify(WxUser wxUser);

    CommonResult list(Pageable pageable, WxUserQuery wxUserQuery);

    CommonResult deleteById(Long id);

    WxUserResult userLogin(WxLoginInfo wxLoginInfo);

    Boolean tokenCheck(String token);
}