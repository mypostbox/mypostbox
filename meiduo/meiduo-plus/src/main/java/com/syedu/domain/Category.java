package com.syedu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName category
 */
@TableName(value ="category")
@Data
public class Category implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer cid;

    /**
     * 
     */
    private String cname;

    /**
     * 
     */
    private String cdesc;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}