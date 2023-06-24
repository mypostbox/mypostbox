package com.syedu.domain;

import lombok.Data;

/**
 * author:Administrator
 * createTime:2023/6/88:38
 */
@Data
public class Goods { //商品表
    private Integer gid;
    private String gname;
    private Float price;
    private String descs;
    private Integer cid; //类型id
}
