package com.syedu.domain;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

/**
 * 
 * @TableName tb_users_user_permissions
 */
@TableName(value ="tb_users_user_permissions")
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class UsersUserPermissions implements Serializable {
    /**
     * 
     */
    @TableId(type = IdType.AUTO)
    private Integer id;

    /**
     * 
     */
    private Integer userId;

    /**
     * 
     */
    private Integer permissionId;

    @TableField(exist = false)
    private static final long serialVersionUID = 1L;
}