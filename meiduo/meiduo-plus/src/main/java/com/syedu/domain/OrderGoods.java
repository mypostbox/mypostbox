package com.syedu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;
import lombok.Data;

/**
 * 
 * @TableName tb_order_goods
 */
@TableName(value ="tb_order_goods")
@Data
public class OrderGoods implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

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
    private Integer count;

    /**
     * 
     */
    private BigDecimal price;

    /**
     * 
     */
    private String comment;

    /**
     * 
     */
    private Integer score;

    /**
     * 
     */
    private Integer isAnonymous;

    /**
     * 
     */
    private Integer isCommented;

    /**
     * 
     */
    private String orderId;

    /**
     * 
     */
    private Integer skuId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}