package com.syedu.domain;

import lombok.Data;

/**
 * author:Administrator
 * createTime:2023/6/722:16
 */
@Data
public class AuthGroupPermissions {
    private Integer id;//组权限id1
    private Integer groupId;//组id
    private Integer permissionId; //组权限
}
