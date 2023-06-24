package com.syedu.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * author:Administrator
 * createTime:2023/6/88:52
 */
@Data
public class TbContentCategory {
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String name;
    private String keyword;
}
