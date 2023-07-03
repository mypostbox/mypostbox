package com.syedu.service;

import com.syedu.domain.GoodsVisit;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_goods_visit】的数据库操作Service
* @createDate 2023-06-13 08:45:37
*/
public interface GoodsVisitService extends IService<GoodsVisit> {

    List<Map<String,Object>> findAllGoodsDayViews(String token) throws Exception;
}
