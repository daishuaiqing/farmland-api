package com.daishuaiqing.farmland.domain;

import javax.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;


@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@SQLDelete(sql = "update gallery set is_deleted=1 where id=?")
@Where(clause = "is_deleted = 0")
@Table(name = "gallery")
@Data
public class Gallery  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /**
     * 上传者id
     */
    @Column(name = "uploader_id")
    private Long uploaderId;

    /**
     * 分类id
     */
    @Column(name = "category_id")
    private Integer categoryId;

    /**
     * 图片地址
     */
    @Column(name = "url")
    private String url;

    /**
     * 收藏数
     */
    @Column(name = "collection_cnt")
    private Integer collectionCnt;

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