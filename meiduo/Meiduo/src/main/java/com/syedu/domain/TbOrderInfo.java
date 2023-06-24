package com.syedu.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * author:Administrator
 * createTime:2023/6/89:06
 */
@Data
public class TbOrderInfo {
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String orderId;
    private Integer totalCount;
    private Float totalAmount;
    private Float freight;
    private Integer payMethod;
    private Integer status;
    private Integer addressId;
    private Integer userId;
}
