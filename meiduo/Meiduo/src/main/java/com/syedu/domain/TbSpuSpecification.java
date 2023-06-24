package com.syedu.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * author:Administrator
 * createTime:2023/6/89:26
 */
@Data
public class TbSpuSpecification {
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private String name;
    private Integer supId;
}
