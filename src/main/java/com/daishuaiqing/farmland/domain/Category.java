package com.daishuaiqing.farmland.domain;

import javax.persistence.*;

import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import java.time.LocalDateTime;


@Entity
@JsonInclude(JsonInclude.Include.NON_NULL)
@SQLDelete(sql = "update category set is_deleted=1 where id=?")
@Where(clause = "is_deleted = 0")
@Table(name = "category")
@Data
public class Category  {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    /**
     * 分类名称
     */
    @Column(name = "cat_name")
    private String catName;

    /**
     * 排序权重，数字越大越靠前
     */
    @Column(name = "order_wt")
    private Integer orderWt;

    /**
     * 小程序是否显示：1是，0否
     */
    @Column(name = "is_show")
    private Integer isShow;

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