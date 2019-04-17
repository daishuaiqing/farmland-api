package com.daishuaiqing.farmland.controller;

import com.daishuaiqing.farmland.dto.WxLoginInfo;
import com.daishuaiqing.farmland.service.WxUserService;
import com.daishuaiqing.farmland.vo.CommonResult;
import io.swagger.annotations.ApiOperation;
import me.chanjar.weixin.common.error.WxErrorException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;


@RestController
public class WxUserController {

    @Autowired
    private WxUserService wxUserService;

    @ApiOperation("微信用户登录")
    @PostMapping("/wx/user/login")
    public CommonResult userLogin(@RequestBody WxLoginInfo wxLoginInfo) throws WxErrorException {
        return new CommonResult().success(wxUserService.userLogin(wxLoginInfo));
    }

    @ApiOperation("微信token校验是否有效")
    @GetMapping("/wx/user/token/check")
    public CommonResult tokenCheck(String token){
        return new CommonResult().success(wxUserService.tokenCheck(token));
    }

    @ApiOperation("单个查询")
    @GetMapping("/wx_user/find/{id}")
    public CommonResult findById(@PathVariable("id") Long id){
        return wxUserService.findById(id);
    }
    /*

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
    }*/
}