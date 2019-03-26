package com.daishuaiqing.farmland.domain;

import javax.persistence.*;
import lombok.Data;
import com.fasterxml.jackson.annotation.JsonInclude;
import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
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
    private Long id;
    @Column(name = "cat_name")
    private String catName;//分类名称
    @Column(name = "order_wt")
    private Integer orderWt;//排序权重，数字越大越靠前
    @Column(name = "is_show")
    private Integer isShow;//小程序是否显示：1是，0否
    @Column(name = "create_time")
    private LocalDateTime createTime;
    @Column(name = "update_time")
    private LocalDateTime updateTime;
    @Column(name = "is_deleted")
    private Integer deleted;

}