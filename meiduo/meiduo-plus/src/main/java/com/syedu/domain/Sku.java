package com.syedu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.Data;

/**
 * 
 * @TableName tb_sku
 */
@TableName(value ="tb_sku")
@Data
public class Sku implements Serializable {
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
    private String name;

    /**
     * 
     */
    private String caption;

    /**
     * 
     */
    private Double price;

    /**
     * 
     */
    private Double costPrice;

    /**
     * 
     */
    private Double marketPrice;

    /**
     * 
     */
    private Integer stock;

    /**
     * 
     */
    private Integer sales;

    /**
     * 
     */
    private Integer comments;

    /**
     * 
     */
    private Integer isLaunched;

    /**
     * 
     */
    private Integer categoryId;

    /**
     * 
     */
    private Integer spuId;

    /**
     * 
     */
    private String defaultImage;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 购物车商品数量count
     */
    @TableField(exist = false)
    private Integer count;
    /**
     * 购物车商品下拉框是否全选
     */
    @TableField(exist = false)
    private boolean selected = true;
    /**
     * 图片
     */
    @TableField(exist = false)
    private List<SkuImage> skuImages;
    /**
     * 商品中间表
     */
    @TableField(exist = false)
    private List<SkuSpecification> skuSpecifications;
    /**
     * spu信息
     */
    @TableField(exist = false)
    private Spu spu;
}