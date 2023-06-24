package com.syedu.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * author:Administrator
 * createTime:2023/6/89:23
 */
@Data
public class TbSpu {
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String name;
    private Integer sales;
    private Integer comments;
    private Integer brandId;
    private Integer category1Id;
    private Integer category2Id;
    private Integer category3Id;
    private String descDetail;
    private String descPack;
    private String descService;
}
