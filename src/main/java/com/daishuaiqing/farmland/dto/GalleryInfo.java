package com.daishuaiqing.farmland.dto;

import lombok.Data;

import java.util.List;

@Data
public class GalleryInfo {
    private List<String> urls;
    private Integer categoryId;
}
