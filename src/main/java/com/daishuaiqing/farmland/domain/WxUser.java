package com.daishuaiqing.farmland.domain;

import javax.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;


@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@SQLDelete(sql = "update wx_user set is_deleted=1 where id=?")
@Where(clause = "is_deleted = 0")
@Table(name = "wx_user")
@Data
public class WxUser  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 用户昵称
     */
    @Column(name = "nick")
    private String nick;

    /**
     * 用户头像
     */
    @Column(name = "avatar")
    private String avatar;

    /**
     * 用户性别：0未知，1男性，2女性
     */
    @Column(name = "gender")
    private Integer gender;

    /**
     * 用户所在国家
     */
    @Column(name = "country")
    private String country;

    /**
     * 用户所在省份
     */
    @Column(name = "province")
    private String province;

    /**
     * 用户所在城市
     */
    @Column(name = "city")
    private String city;

    /**
     * 手机号码
     */
    @Column(name = "telephone")
    private String telephone;

    /**
     * 用户openID
     */
    @Column(name = "openid")
    private String openid;

    /**
     * 用户的UnionID
     */
    @Column(name = "unionid")
    private String unionid;

    /**
     * 会话密钥
     */
    @Column(name = "session_key")
    private String sessionKey;

    /**
     * 是否是上传者：1是，0否
     */
    @Column(name = "is_uploader")
    private Integer isUploader;

    /**
     * 创建时间
     */
    @Column(name = "create_time")
    private LocalDateTime createTime;

    /**
     * 上次更新时间
     */
    @Column(name = "update_time")
    private LocalDateTime updateTime;

    /**
     * 是否删除
     */
    @Column(name = "is_deleted")
    private Integer deleted;


}