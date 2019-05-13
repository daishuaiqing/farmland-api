package com.daishuaiqing.farmland.service;

import com.daishuaiqing.farmland.domain.WxPanel;
import com.daishuaiqing.farmland.query.WxPanelQuery;
import com.daishuaiqing.farmland.vo.CommonResult;
import org.springframework.data.domain.Pageable;

public interface WxPanelService {

    CommonResult findByWxUserId(Long id);

    CommonResult findAll();

    CommonResult add(WxPanel wxPanel);

    CommonResult modify(WxPanel wxPanel);

    CommonResult list(Pageable pageable, WxPanelQuery wxPanelQuery);

    CommonResult deleteById(Long id);

    void updateUploadCnt(Long wxUid, int size);
}