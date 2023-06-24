package com.syedu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;
import lombok.Data;

/**
 * 
 * @TableName auth_group_permissions
 */
@TableName(value ="auth_group_permissions")
@Data
public class AuthGroupPermissions implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer groupId;

    /**
     * 
     */
    private Integer permissionId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}