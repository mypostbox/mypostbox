package com.syedu.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * author:Administrator
 * createTime:2023/6/88:40
 */
@Data
public class TbAddress { //地址表
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String title;
    private String receiver;
    private String place;
    private String mobile;
    private String tel;
    private String email;
    private Integer isDelete;
    private Integer cityId;
    private Integer districtId;
    private Integer provinceId;
    private Integer userId;
}
