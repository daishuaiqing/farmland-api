package com.daishuaiqing.farmland.dto;

import lombok.Data;

@Data
public class UserInfo {
    /**
     * 用户昵称
     */
    private String nick;

    /**
     * 用户头像
     */
    private String avatar;

    /**
     * 用户性别：0未知，1男性，2女性
     */
    private Integer gender;

    /**
     * 用户所在国家
     */
    private String country;

    /**
     * 用户所在省份
     */
    private String province;

    /**
     * 用户所在城市
     */
    private String city;

}
