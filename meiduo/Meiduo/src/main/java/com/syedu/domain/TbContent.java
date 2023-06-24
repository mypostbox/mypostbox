package com.syedu.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * author:Administrator
 * createTime:2023/6/88:48
 */
@Data
public class TbContent {
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String title;
    private String url;
    private String image;
    private String text;
    private Integer sequence; //排序规则
    private Integer status;
    private Integer categoryId;
    private Integer skuCategoryId;
}
