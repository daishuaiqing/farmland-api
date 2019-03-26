package com.daishuaiqing.farmland.controller;

import com.daishuaiqing.farmland.vo.CommonResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloControler {
    @GetMapping("/hello")
    public CommonResult hello(){
        return new CommonResult().success("hello");
    }
}
