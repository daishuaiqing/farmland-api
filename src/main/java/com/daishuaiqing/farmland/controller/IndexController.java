package com.daishuaiqing.farmland.controller;

import com.daishuaiqing.farmland.service.IndexService;
import com.daishuaiqing.farmland.vo.CommonResult;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 小程序首页接口
 */
@RestController
public class IndexController {
    @Autowired
    private IndexService indexService;
    /**
     * 首页信息
     * 分类信息，分类下图片信息，初次加载
     * @return
     */
    @GetMapping("/wx/home/index")
    public CommonResult index(){
        return new CommonResult().success(indexService.index());
    }
}
