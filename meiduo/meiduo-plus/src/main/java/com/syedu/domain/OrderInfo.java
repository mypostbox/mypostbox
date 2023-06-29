package com.syedu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 
 * @TableName tb_order_info
 */
@TableName(value ="tb_order_info")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
public class OrderInfo implements Serializable {
    /**
     * 
     */
    @TableId
    private String orderId;

    /**
     * 
     */
    private Date createTime;

    /**
     * 
     */
    private Date updateTime;

    /**
     * 
     */
    private Integer totalCount;

    /**
     * 
     */
    private Double totalAmount;

    /**
     * 
     */
    private Double freight;

    /**
     * 
     */
    private Integer payMethod;

    /**
     * 
     */
    private Integer status;

    /**
     * 
     */
    private Integer addressId;

    /**
     * 
     */
    private Integer userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 订单信息的商品
     */
    @TableField(exist = false)
    private List<OrderGoods> orderGoods;
}