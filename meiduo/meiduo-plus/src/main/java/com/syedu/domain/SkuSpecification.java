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
 * 明细单表
 * @TableName tb_sku_specification
 */
@TableName(value ="tb_sku_specification")
@Data
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
    /**
     *
     */
    private String specName;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;

    /**
     * 具体详选项
     */
    @TableField(exist = false)
    private List<SpecificationOption> specificationOptions;
}