package com.daishuaiqing.farmland.dto;

import lombok.Data;

@Data
public class WxLoginInfo {
    private String code;
    private UserInfo userInfo;
}
