package com.daishuaiqing.farmland.vo;

import lombok.Data;

@Data
public class WxUserResult {
    /**
     * 微信用户昵称
     */
    private String nick;
    /**
     *微信用户头像地址
     */
    private String avatar;
    /**
     *微信用户性别，男：女：未知
     */
    private String gender;
    /**
     *是否上传者，1 是，0 否
     */
    private Integer uploader;
    /**
     * token
     */
    private String token;
}
