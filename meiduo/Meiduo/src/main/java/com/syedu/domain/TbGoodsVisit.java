package com.syedu.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * author:Administrator
 * createTime:2023/6/88:59
 */
@Data
public class TbGoodsVisit {//商品浏览记录表
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer count; //浏览数量
    private LocalDate date;//浏览时间
    private Integer categoryId;//种类id
}
