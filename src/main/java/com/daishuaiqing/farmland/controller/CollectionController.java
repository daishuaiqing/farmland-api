package com.daishuaiqing.farmland.controller;

import com.daishuaiqing.farmland.domain.Collection;
import com.daishuaiqing.farmland.service.CollectionService;
import com.daishuaiqing.farmland.query.CollectionQuery;
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
public class CollectionController {

    @Autowired
    private CollectionService collectionService;

    @ApiOperation("单个查询")
    @GetMapping("/collection/find/{id}")
    public CommonResult findById(@PathVariable("id") Long id){
        return collectionService.findById(id);
    }

    @ApiOperation("全部")
    @GetMapping("/collection/find/all")
    public CommonResult findAll(){
        return collectionService.findAll();
    }

    @ApiOperation("新增")
    @PostMapping("/collection/add")
    public CommonResult add(@Valid @ApiParam @RequestBody Collection collection,
                            BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new CommonResult().validateFailed(bindingResult.getFieldError().getDefaultMessage());
        }else {
            return collectionService.add(collection);
        }
    }

    @ApiOperation("删除")
    @GetMapping("/collection/delete/{id}")
    public CommonResult deleteById(@PathVariable("id") Long id) {
        return collectionService.deleteById(id);
    }

    @ApiOperation("修改")
    @PostMapping("/collection/modify/{id}")
    public CommonResult modify(@PathVariable(name = "id",required = true) Long id,
                               @Valid @ApiParam @RequestBody Collection collection,
                               BindingResult bindingResult) {
        collection.setId(id);
        if(bindingResult.hasErrors()){
            return new CommonResult().validateFailed(bindingResult.getFieldError().getDefaultMessage());
        }else {
            return collectionService.modify(collection);
        }
    }

    @ApiOperation("列表查询")
    @GetMapping("/collection/list")
    public CommonResult list(PageParam page, CollectionQuery collectionQuery) {
        Pageable pageable = PageRequest.of(page.getPage(),page.getSize());
        return collectionService.list(pageable,collectionQuery);
    }
}