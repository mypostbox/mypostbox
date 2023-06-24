package com.syedu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
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

    /**
     * 
     */
    private Integer category1Id;

    /**
     * 
     */
    private Integer category2Id;

    /**
     * 
     */
    private Integer category3Id;

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