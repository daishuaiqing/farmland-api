package com.daishuaiqing.farmland.controller;

import com.daishuaiqing.farmland.domain.WxUser;
import com.daishuaiqing.farmland.service.WxUserService;
import com.daishuaiqing.farmland.query.WxUserQuery;
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
public class WxUserController {

    @Autowired
    private WxUserService wxUserService;

    @ApiOperation("单个查询")
    @GetMapping("/wx_user/find/{id}")
    public CommonResult findById(@PathVariable("id") Long id){
        return wxUserService.findById(id);
    }

    @ApiOperation("全部")
    @GetMapping("/wx_user/find/all")
    public CommonResult findAll(){
        return wxUserService.findAll();
    }

    @ApiOperation("新增")
    @PostMapping("/wx_user/add")
    public CommonResult add(@Valid @ApiParam @RequestBody WxUser wxUser,
                            BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new CommonResult().validateFailed(bindingResult.getFieldError().getDefaultMessage());
        }else {
            return wxUserService.add(wxUser);
        }
    }

    @ApiOperation("删除")
    @GetMapping("/wx_user/delete/{id}")
    public CommonResult deleteById(@PathVariable("id") Long id) {
        return wxUserService.deleteById(id);
    }

    @ApiOperation("修改")
    @PostMapping("/wx_user/modify/{id}")
    public CommonResult modify(@PathVariable(name = "id",required = true) Long id,
                               @Valid @ApiParam @RequestBody WxUser wxUser,
                               BindingResult bindingResult) {
        wxUser.setId(id);
        if(bindingResult.hasErrors()){
            return new CommonResult().validateFailed(bindingResult.getFieldError().getDefaultMessage());
        }else {
            return wxUserService.modify(wxUser);
        }
    }

    @ApiOperation("列表查询")
    @GetMapping("/wx_user/list")
    public CommonResult list(PageParam page, WxUserQuery wxUserQuery) {
        Pageable pageable = PageRequest.of(page.getPage(),page.getSize());
        return wxUserService.list(pageable,wxUserQuery);
    }
}