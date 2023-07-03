package com.syedu.controller.admins;


import com.syedu.domain.GoodsCategory;
import com.syedu.domain.Sku;
import com.syedu.domain.Spu;
import com.syedu.domain.SpuSpecification;
import com.syedu.service.GoodsCategoryService;
import com.syedu.service.SkuService;
import com.syedu.service.SpuService;
import com.syedu.service.SpuSpecificationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;


/**
 * author:Administrator
 * createTime:2023/7/310:35
 */
@RestController
@RequestMapping("admins")
public class AdminSkuController {
    @Autowired
    private SkuService skuService;
    @Autowired
    private GoodsCategoryService goodsCategoryService;
    @Autowired
    private SpuService spuService;
    @Autowired
    private SpuSpecificationService spuSpecificationService;
    /**
     * 分页获取所有的商品sku(或根据关键字查找)
     */
    @GetMapping("skus")
    Map<String,Object> findAllSkuByPage(@RequestHeader("Authorization") String token,
                                        @RequestParam("page") Integer page,
                                        @RequestParam("pagesize") Integer pageSize,
                                        @RequestParam("keyword") String keyword) throws Exception {
        return this.skuService.findAllSkuByPage(token, page, pageSize, keyword);
    }
    /**
     * 存储sku商品信息(包含skuSpecification)
     * @param token
     * @param sku
     * @return
     */
    @PostMapping("/skus")
    Sku saveSku(@RequestHeader("Authorization") String token,
                Sku sku) throws Exception {
        return this.skuService.saveSkuAndSkuSpecification(token,sku);
    }
    /**
     * 获取商品种类goodsCategory
     */
    @GetMapping("skus/categories")
    List<GoodsCategory> findGoodsCategory(@RequestHeader("Authorization") String token) throws Exception {
        return this.goodsCategoryService.findGoodsCategory(token);
    }
    //获取sku的信息
    @GetMapping("skus/{skuId}")
    Sku findSku(@RequestHeader("Authorization") String token,
                @PathVariable("skuId") Integer skuId) throws Exception {
        return this.skuService.findSku(token,skuId);
    }
    //更新sku的信息
    @PutMapping("skus/{skuId}")
    Sku updateSku(@RequestHeader("Authorization") String token,
                  @PathVariable("skuId") Integer skuId,
                  @RequestBody Sku sku) throws Exception {
        return this.skuService.updateSku(token,skuId,sku);
    }
    //删除sku
    @DeleteMapping("skus/{skuId}")
    Integer deleteSku(@RequestHeader("Authorization") String token,
                      @PathVariable("skuId") Integer skuId) throws Exception {
        return this.skuService.deleteSku(token, skuId);
    }
    //获取商品的spu
    @GetMapping("goods/simple")
    List<Spu> findSpu(@RequestHeader("Authorization") String token) throws Exception {
        return this.spuService.findSpu(token);
    }
    //根据spuId获取SpuSpecification(包含options)
    @GetMapping("goods/{spuId}/specs")
    List<Map<String,Object>> findSpuSpecification(@RequestHeader("Authorization") String token,
                                                @PathVariable("spuId") Integer spuId) throws Exception {
        return this.spuSpecificationService.findSpuSpecificationBySpuId(token,spuId);
    }
    //分页查找spu数据
    @GetMapping("goods")
    Map<String,Object> findAllSpuByPage(@RequestHeader("Authorization") String token,
                                              @RequestParam("page") Integer page,
                                              @RequestParam("pagesize") Integer pageSize) throws Exception {
        return this.spuService.findAllSpuByPage(token, page, pageSize);
    }

    //分页查找specs数据
    @GetMapping("goods/specs")
    Map<String,Object> findAllSpecsByPage(@RequestHeader("Authorization") String token,
                                          @RequestParam("page") Integer page,
                                          @RequestParam("pagesize") Integer pageSize) throws Exception {
        return this.spuSpecificationService.findAllSpecsByPage(token, page, pageSize);
    }
    //添加保存数据SpuSpec
    @PostMapping("goods/specs")
    Integer saveSpuSpec(@RequestHeader("Authorization") String token,
                        SpuSpecification spuSpecification) throws Exception {
        return this.spuSpecificationService.saveSpuSpec(token, spuSpecification);
    }
    //根据specId修改spec
    @PutMapping("goods/specs/{specsId}")
    Integer updateSpuSpec(@RequestHeader("Authorization") String token,
                          @PathVariable("specsId") Integer specsId,
                          @RequestBody SpuSpecification spuSpecification) throws Exception {
        return this.spuSpecificationService.updateSpuSpec(token, specsId, spuSpecification);
    }
    //根据specId删除spec
    @DeleteMapping("goods/specs/{specsId}")
    Integer deleteSpuSpec(@RequestHeader("Authorization") String token,
                          @PathVariable("specsId") Integer specsId) throws Exception {
        return this.spuSpecificationService.deleteSpuSpec(token, specsId);
    }
    //根据specsId获取specs
    @GetMapping("goods/specs/{specId}")
    Map<String,Object> findSpuSpecsById(@RequestHeader("Authorization") String token,
                                        @PathVariable("specId") Integer specId) throws Exception {
        return this.spuSpecificationService.findSpuSpecById(token, specId);
    }

}
