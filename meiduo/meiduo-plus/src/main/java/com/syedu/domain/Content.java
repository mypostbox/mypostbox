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
 * @TableName tb_content
 */
@TableName(value ="tb_content")
@Data
public class Content implements Serializable {
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
    private String title;

    /**
     * 
     */
    private String url;

    /**
     * 
     */
    private String image;

    /**
     * 
     */
    private String text;

    /**
     * 
     */
    private Integer sequence;

    /**
     * 
     */
    private Integer status;

    /**
     * 
     */
    private Integer categoryId;

    /**
     * 
     */
    private Integer goodsCategoryId;

    /**
     *
     */
    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 自关联容器
     */

}