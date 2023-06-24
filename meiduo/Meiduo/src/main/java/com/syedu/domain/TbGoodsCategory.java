package com.syedu.domain;

import lombok.Data;

import java.time.LocalDateTime;
import java.util.List;

/**
 * author:Administrator
 * createTime:2023/6/88:54
 */
@Data
public class TbGoodsCategory { //商品种类表
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String name;
    private Integer parentId;

    //子类
    private List<TbGoodsCategory> child;
}
