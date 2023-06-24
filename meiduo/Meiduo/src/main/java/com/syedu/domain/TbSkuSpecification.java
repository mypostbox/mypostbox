package com.syedu.domain;

import lombok.Data;

import java.time.LocalDateTime;

/**
 * author:Administrator
 * createTime:2023/6/89:17
 */
@Data
public class TbSkuSpecification { //库存说明表
    private Integer id;
    private LocalDateTime createTime;
    private LocalDateTime updateTime;
    private Integer optionId; //选项id
    private Integer skuId;
    private Integer specId;
}
