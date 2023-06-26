package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.Sku;
import com.syedu.domain.SkuSpecification;
import com.syedu.mapper.SkuMapper;
import com.syedu.service.SkuSpecificationService;
import com.syedu.mapper.SkuSpecificationMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * @author Administrator
 * @description 针对表【tb_sku_specification】的数据库操作Service实现
 * @createDate 2023-06-13 08:45:37
 */
@Service
public class SkuSpecificationServiceImpl extends ServiceImpl<SkuSpecificationMapper, SkuSpecification>
        implements SkuSpecificationService {
    @Autowired
    private SkuSpecificationMapper skuSpecificationMapper;
    @Autowired
    private SkuMapper skuMapper;

    @Override
    public Sku detailToggleService(String arr) {
        Integer skuId = findSkuId(arr);
        if (skuId == null) return null;
        return this.skuMapper.findAllByIdWithSpecificationAndOption(skuId);
    }

    /**
     * 找到skuId
     * @param arr
     * @return
     */
    private Integer findSkuId(String arr) {
        String[] split = arr.split(",");
        List<List<SkuSpecification>> collect = Stream.of(split).map(Integer::parseInt).map(skuSpecificationMapper::findAllByOptionId).collect(Collectors.toList());
        if (collect.size() == 3) {
            for (int i = 0; i < collect.get(0).size(); i++) {
                for (int i1 = 0; i1 < collect.get(1).size(); i1++) {
                    for (int i2 = 0; i2 < collect.get(2).size(); i2++) {
                        Integer skuId = collect.get(0).get(i).getSkuId();
                        Integer skuId1 = collect.get(1).get(i1).getSkuId();
                        Integer skuId2 = collect.get(2).get(i2).getSkuId();
                        if (skuId == skuId1 && skuId == skuId2) {
                            return skuId;
                        }
                    }
                }
            }
        } else {
            for (int i = 0; i < collect.get(0).size(); i++) {
                for (int i1 = 0; i1 < collect.get(1).size(); i1++) {
                    Integer skuId = collect.get(0).get(i).getSkuId();
                    Integer skuId1 = collect.get(1).get(i1).getSkuId();
                    if(skuId == skuId1){
                        return skuId;
                    }
                }
            }
        }
        return null;
    }

}




