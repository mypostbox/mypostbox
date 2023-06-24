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
 * @TableName tb_address
 */
@TableName(value ="tb_address")
@Data
public class Address implements Serializable {
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
    private String receiver;

    /**
     * 
     */
    private String place;

    /**
     * 
     */
    private String mobile;

    /**
     * 
     */
    private String tel;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private Integer isDeleted;

    /**
     * 
     */
    private Integer cityId;

    /**
     * 
     */
    private Integer districtId;

    /**
     * 
     */
    private Integer provinceId;

    /**
     * 
     */
    private Integer userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}