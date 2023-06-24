package com.syedu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import java.util.Date;
import lombok.Data;
import lombok.experimental.Accessors;

/**
 * 
 * @TableName tb_users
 */
@TableName(value ="tb_users")
@Data
@Accessors(chain = true)
public class Users implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private String password;

    /**
     * 
     */
    private Date lastLogin;

    /**
     * 
     */
    private Integer isSuperuser;

    /**
     * 
     */
    private String username;

    /**
     * 
     */
    private String firstName;

    /**
     * 
     */
    private String lastName;

    /**
     * 
     */
    private String email;

    /**
     * 
     */
    private Integer isStaff;

    /**
     * 
     */
    private Integer isActive;

    /**
     * 
     */
    private Date dateJoined;

    /**
     * 
     */
    private String mobile;

    /**
     * 
     */
    private Integer emailActive;

    /**
     * 
     */
    private Integer defaultAddressId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
    /**
     * 封装token的容器
     */
    @TableField(exist = false)
    private String token;
    /**
     * 校验密码的容器
     */
    @TableField(exist = false)
    private String password2;
    /**
     * 验证码的容器
     */
    @TableField(exist = false)
    private String sms_code;
    /**
     * 容器
     */
    @TableField(exist = false)
    private String allow;
}