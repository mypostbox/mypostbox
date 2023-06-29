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

/**
 * 
 * @TableName tb_content_category
 */
@TableName(value ="tb_content_category")
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ContentCategory implements Serializable {
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
    private String keyword;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 自关联容器
     */
    @TableField(exist = false)
    private List<ContentCategory> child;
    /**
     * 信息容器
     */
    @TableField(exist = false)
    private List<Content> contents;


}