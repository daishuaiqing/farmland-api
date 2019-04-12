package com.daishuaiqing.farmland.controller;

import com.daishuaiqing.farmland.domain.WxFeedback;
import com.daishuaiqing.farmland.service.WxFeedbackService;
import com.daishuaiqing.farmland.query.WxFeedbackQuery;
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
public class WxFeedbackController {

    @Autowired
    private WxFeedbackService wxFeedbackService;

    @ApiOperation("单个查询")
    @GetMapping("/wx_feedback/find/{id}")
    public CommonResult findById(@PathVariable("id") Long id){
        return wxFeedbackService.findById(id);
    }

    @ApiOperation("全部")
    @GetMapping("/wx_feedback/find/all")
    public CommonResult findAll(){
        return wxFeedbackService.findAll();
    }

    @ApiOperation("新增")
    @PostMapping("/wx_feedback/add")
    public CommonResult add(@Valid @ApiParam @RequestBody WxFeedback wxFeedback,
                            BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new CommonResult().validateFailed(bindingResult.getFieldError().getDefaultMessage());
        }else {
            return wxFeedbackService.add(wxFeedback);
        }
    }

    @ApiOperation("删除")
    @GetMapping("/wx_feedback/delete/{id}")
    public CommonResult deleteById(@PathVariable("id") Long id) {
        return wxFeedbackService.deleteById(id);
    }

    @ApiOperation("修改")
    @PostMapping("/wx_feedback/modify/{id}")
    public CommonResult modify(@PathVariable(name = "id",required = true) Long id,
                               @Valid @ApiParam @RequestBody WxFeedback wxFeedback,
                               BindingResult bindingResult) {
        wxFeedback.setId(id);
        if(bindingResult.hasErrors()){
            return new CommonResult().validateFailed(bindingResult.getFieldError().getDefaultMessage());
        }else {
            return wxFeedbackService.modify(wxFeedback);
        }
    }

    @ApiOperation("列表查询")
    @GetMapping("/wx_feedback/list")
    public CommonResult list(PageParam page, WxFeedbackQuery wxFeedbackQuery) {
        Pageable pageable = PageRequest.of(page.getPage(),page.getSize());
        return wxFeedbackService.list(pageable,wxFeedbackQuery);
    }
}