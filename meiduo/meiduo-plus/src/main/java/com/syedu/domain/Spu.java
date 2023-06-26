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
 * @TableName tb_spu
 */
@TableName(value ="tb_spu")
@Data
public class Spu implements Serializable {
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
    private Integer sales;

    /**
     * 
     */
    private Integer comments;

    /**
     * 
     */
    private Integer brandId;
    @TableField(exist = false)
    private Brand brand;

    /**
     * 
     */
    private Integer category1Id;
    @TableField(exist = false)
    private GoodsCategory category1;
    /**
     * 
     */
    private Integer category2Id;
    @TableField(exist = false)
    private GoodsCategory category2;
    /**
     * 
     */
    private Integer category3Id;
    @TableField(exist = false)
    private GoodsCategory category3;
    /**
     * 
     */
    private String descDetail;

    /**
     * 
     */
    private String descPack;

    /**
     * 
     */
    private String descService;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

}