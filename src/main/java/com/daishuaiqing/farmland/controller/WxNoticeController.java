package com.daishuaiqing.farmland.controller;

import com.daishuaiqing.farmland.domain.WxNotice;
import com.daishuaiqing.farmland.service.WxNoticeService;
import com.daishuaiqing.farmland.query.WxNoticeQuery;
import com.daishuaiqing.farmland.vo.CommonResult;
import com.daishuaiqing.farmland.query.PageParam;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class WxNoticeController {

    @Autowired
    private WxNoticeService wxNoticeService;

    @ApiOperation("单个查询")
    @GetMapping("/wx_notice/find/{id}")
    public CommonResult findById(@PathVariable("id") Long id){
        return wxNoticeService.findById(id);
    }

    @ApiOperation("全部")
    @GetMapping("/wx_notice/find/all")
    public CommonResult findAll(){
        return wxNoticeService.findAll();
    }

    @ApiOperation("新增")
    @PostMapping("/wx_notice/add")
    public CommonResult add(@Valid @ApiParam @RequestBody WxNotice wxNotice,
                            BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new CommonResult().validateFailed(bindingResult.getFieldError().getDefaultMessage());
        }else {
            return wxNoticeService.add(wxNotice);
        }
    }

    @ApiOperation("删除")
    @GetMapping("/wx_notice/delete/{id}")
    public CommonResult deleteById(@PathVariable("id") Long id) {
        return wxNoticeService.deleteById(id);
    }

    @ApiOperation("修改")
    @PostMapping("/wx_notice/modify/{id}")
    public CommonResult modify(@PathVariable(name = "id",required = true) Long id,
                               @Valid @ApiParam @RequestBody WxNotice wxNotice,
                               BindingResult bindingResult) {
        wxNotice.setId(id);
        if(bindingResult.hasErrors()){
            return new CommonResult().validateFailed(bindingResult.getFieldError().getDefaultMessage());
        }else {
            return wxNoticeService.modify(wxNotice);
        }
    }

    @ApiOperation("列表查询")
    @GetMapping("/wx_notice/list")
    public CommonResult list(PageParam page, WxNoticeQuery wxNoticeQuery) {
        Pageable pageable = PageRequest.of(page.getPage(),page.getSize());
        return wxNoticeService.list(pageable,wxNoticeQuery);
    }
}