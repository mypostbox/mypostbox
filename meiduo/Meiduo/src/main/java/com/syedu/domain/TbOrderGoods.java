package com.syedu.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * author:Administrator
 * createTime:2023/6/89:03
 */
@Data
public class TbOrderGoods { //订单商品表
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer count;
    private Float price;
    private String comment;
    private Integer score;
    private Integer isAnonymous;
    private Integer isCommented;
    private String orderId;
    private Integer skuId;
}
