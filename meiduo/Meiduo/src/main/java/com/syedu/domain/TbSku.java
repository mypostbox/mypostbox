package com.syedu.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * author:Administrator
 * createTime:2023/6/89:11
 */
@Data
public class TbSku { //库存表
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String name;
    private String caption;
    private Float price;
    private Float costPrice;
    private Float marketPrice;
    private Integer stock;
    private Integer sales;
    private Integer comments;
    private Integer isLaunched;
    private Integer categoryId;
    private Integer spuId;
    private String defaultImage;
}
