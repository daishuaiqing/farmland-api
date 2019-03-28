package com.daishuaiqing.farmland.controller;

import com.daishuaiqing.farmland.domain.Category;
import com.daishuaiqing.farmland.query.PageParam;
import com.daishuaiqing.farmland.service.CategoryService;
import com.daishuaiqing.farmland.query.CategoryQuery;
import com.daishuaiqing.farmland.vo.CommonResult;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@RestController
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @ApiOperation("单个查询")
    @GetMapping("/category/find/{id}")
    public CommonResult findById(@PathVariable("id") Long id){
        return categoryService.findById(id);
    }

    @ApiOperation("全部")
    @GetMapping("/category/find/all")
    public CommonResult findAll(){
        return new CommonResult().success(categoryService.findAll());
    }

    @ApiOperation("新增")
    @PostMapping("/category/add")
    public CommonResult add(@Valid @ApiParam @RequestBody Category category,
                            BindingResult bindingResult) {
        if(bindingResult.hasErrors()){
            return new CommonResult().validateFailed(bindingResult.getFieldError().getDefaultMessage());
        }else {
            return categoryService.add(category);
        }
    }

    @ApiOperation("删除")
    @GetMapping("/category/delete/{id}")
    public CommonResult deleteById(@PathVariable("id") Long id) {
        return categoryService.deleteById(id);
    }

    @ApiOperation("修改")
    @PostMapping("/category/modify/{id}")
    public CommonResult modify(@PathVariable(name = "id",required = true) Integer id,
                               @Valid @ApiParam @RequestBody Category category,
                               BindingResult bindingResult) {
        category.setId(id);
        if(bindingResult.hasErrors()){
            return new CommonResult().validateFailed(bindingResult.getFieldError().getDefaultMessage());
        }else {
            return categoryService.modify(category);
        }
    }

    @ApiOperation("列表查询")
    @GetMapping("/category/list")
    public CommonResult list(PageParam page, CategoryQuery categoryQuery) {
        Pageable pageable = PageRequest.of(page.getPage(),page.getSize());
        return categoryService.list(pageable,categoryQuery);
    }
}