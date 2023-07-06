package com.syedu.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * author:Administrator
 * createTime:2023/7/510:08
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MyGroupBody {
    private String name ;
    private List<Integer> permissions;
}
