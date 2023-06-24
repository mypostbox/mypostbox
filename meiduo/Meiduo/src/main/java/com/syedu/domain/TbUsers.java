package com.syedu.domain;

import lombok.Data;

import java.time.LocalDate;
import java.time.LocalDateTime;

/**
 * author:Administrator
 * createTime:2023/6/89:27
 */
@Data
public class TbUsers {
    private Integer id;
    private String password;
    private LocalDate lastLogin;
    private Integer isSuperuser;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private Integer isStaff;
    private Integer isActive;
    private LocalDateTime dateJoined;
    private String mobile;
    private Integer emailActive;
    private Integer defaultAddressId;
}
