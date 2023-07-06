package com.syedu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;
import org.springframework.beans.factory.annotation.Autowired;

/**
 * 
 * @TableName tb_sku_image
 */
@TableName(value ="tb_sku_image")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class SkuImage implements Serializable {
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
    private String image;

    /**
     * 
     */
    private Integer skuId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}