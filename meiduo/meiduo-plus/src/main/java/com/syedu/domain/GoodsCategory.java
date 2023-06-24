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
 * @TableName tb_goods_category
 */
@TableName(value ="tb_goods_category")
@Data
public class GoodsCategory implements Serializable {
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
    private Integer parentId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 自关联容器
     */
    @TableField(exist = false)
    private List<GoodsCategory> child;
}