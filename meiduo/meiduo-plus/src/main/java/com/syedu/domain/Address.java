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

/**
 * 
 * @TableName tb_address
 */
@TableName(value ="tb_address")
@Data
@NoArgsConstructor
@AllArgsConstructor
@Accessors(chain=true)
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
    @TableField(exist = false)
    private String city;

    /**
     * 
     */
    private Integer districtId;
    @TableField(exist = false)
    private String district;

    /**
     * 
     */
    private Integer provinceId;
    @TableField(exist = false)
    private String province;

    /**
     * 
     */
    private Integer userId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}