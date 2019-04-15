package com.daishuaiqing.farmland.dto;

import lombok.Data;

/**
 * Redis 中存放的微信用户信息
 */
@Data
public class WxUserTokenInfo {

    /**
     * 微信用户在系统中的ID
     */
    private Long WxUserId;

    /**
     * 微信用户的openID
     */
    private String openid;

    /**
     * 微信用户的昵称
     */
    private String nick;

    /**
     * 微信用户的性别
     * 1：男
     * 2：女
     * 0：未知
     */
    private Integer gender;

    /**
     * 用户的手机号码
     * 未绑定时为空字符串
     */
    private String telephone;

}
