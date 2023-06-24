package com.syedu.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * author:Administrator
 * createTime:2023/6/89:20
 */
@Data
public class TbSpecificationOption { //说明选项表
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String value;
    private Integer specId;
}
