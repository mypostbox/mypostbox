package com.syedu.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * author:Administrator
 * createTime:2023/6/88:45
 */
@Data
public class TbBrand {
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String name;
    private String logo;
    private String firstLetter;
}
