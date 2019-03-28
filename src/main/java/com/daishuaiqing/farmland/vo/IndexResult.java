package com.daishuaiqing.farmland.vo;

import com.daishuaiqing.farmland.domain.Category;
import com.daishuaiqing.farmland.domain.Gallery;
import lombok.Data;

import java.util.List;

/**
 * 小程序首页返回的数据
 */
@Data
public class IndexResult {

    /**
     * 分类的集合
     */
    private List<Category> categories;

    /**
     * 第一个分类下的图片集合
     */
    private List<Gallery> galleries;

}
