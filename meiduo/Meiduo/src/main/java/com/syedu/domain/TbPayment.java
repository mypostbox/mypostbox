package com.syedu.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * author:Administrator
 * createTime:2023/6/89:09
 */
@Data
public class TbPayment { //支付管理表
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime ;
    private String tradeId;//
    private String orderId;//订单id

}
