package com.syedu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;

import java.util.List;

/**
 * author:Administrator
 * createTime:2023/7/514:09
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Accessors(chain = true)
public class MyUserBody {
    private String username;
    private String password;
    private String mobile;
    private String email;
    private List<Integer> groups;//角色id
    private List<Integer> user_permissions;//权限id
}
