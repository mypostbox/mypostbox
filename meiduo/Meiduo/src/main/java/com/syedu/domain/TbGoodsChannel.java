package com.syedu.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * author:Administrator
 * createTime:2023/6/88:57
 */
@Data
public class TbGoodsChannel { //商品频道表
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer sequence;
    private String url;
    private Integer groupId;
    private Integer categoryId;
}
