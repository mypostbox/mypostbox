package com.syedu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 明细单表
 * @TableName tb_sku_specification
 */
@TableName(value ="tb_sku_specification")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SkuSpecification implements Serializable {
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
    private Integer optionId;

    /**
     * 
     */
    private Integer skuId;

    /**
     * 
     */
    private Integer specId;


    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 选项种类
     */
    @TableField(exist = false)
    private SpuSpecification spuSpecifications;

}