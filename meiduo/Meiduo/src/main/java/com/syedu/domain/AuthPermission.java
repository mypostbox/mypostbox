package com.syedu.domain;

import lombok.Data;

/**
 * author:Administrator
 * createTime:2023/6/722:20
 */
@Data
public class AuthPermission { //权限表
    private Integer id;
    private String name;
    private Integer contentTypeId;
    private String codename;
}
