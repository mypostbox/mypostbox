package com.syedu.controller.admins;


import com.syedu.domain.*;
import com.syedu.service.*;
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
    @Autowired
    private SpecificationOptionService specificationOptionService;
    @Autowired
    private GoodsChannelService goodsChannelService;
    @Autowired
    private ChannelGroupService channelGroupService;
    @Autowired
    private BrandService brandService;
    @Autowired
    private SkuImageService skuImageService;
    //SKU管理
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
    @PostMapping("skus")
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

    //规格管理
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
    //获取options的类型
    @GetMapping("goods/specs/simple")
    List<SpuSpecification> findAllSpuSpec(@RequestHeader("Authorization") String token) throws Exception {
        return this.spuSpecificationService.findAllSpuSpec(token);
    }

    //频道管理
    //分页获取goodsChannel的数据
    @GetMapping("goods/channels")
    Map<String,Object> findAllChannelByPage(@RequestHeader("Authorization") String token,
                                            @RequestParam("page") Integer page,
                                            @RequestParam("pagesize") Integer pageSize) throws Exception {
        return this.goodsChannelService.findAllChannelByPage(token, page, pageSize);
    }
    //获取goodsChannel的group的所有类型
    @GetMapping("goods/channel_types")
    List<ChannelGroup> findAllChannelGroup(@RequestHeader("Authorization") String token) throws Exception {
        return this.channelGroupService.findAllChannelGroup(token);
    }
    //获取goodsChannel的category的所有类型
    @GetMapping("goods/categories")
    List<GoodsCategory> fndAllGoodsCategory(@RequestHeader("Authorization") String token) throws Exception {
        return this.goodsCategoryService.findGoodsCategoryOne(token);
    }
    //添加保存goodsChannel
    @PostMapping("goods/channels")
    Integer saveGoodsChannel(@RequestHeader("Authorization") String token,
                             GoodsChannel goodsChannel) throws Exception {
        return this.goodsChannelService.saveGoodsChannel(token, goodsChannel);
    }
    //根据goodsChannelId找goodsChannel
    @GetMapping("goods/channels/{goodsChannelId}")
    Map<String,Object> findGoodsChannel(@RequestHeader("Authorization") String token,
                                       @PathVariable("goodsChannelId") Integer goodsChannelId){
        return null;
    }
    //根据goodsChannelId更新goodsChannel
    @PutMapping("goods/channels/{goodsChannelId}")
    Integer updateGoodsChannel(@RequestHeader("Authorization") String token,
                               @PathVariable("goodsChannelId") Integer goodsChannelId,
                               @RequestBody GoodsChannel goodsChannel) throws Exception {
        return this.goodsChannelService.updateGoodsChannel(token, goodsChannelId, goodsChannel);
    }
    //根据goodsChannelId删除goodsChannel
    @DeleteMapping("goods/channels/{goodsChannelId}")
    Integer deleteGoodsChannel(@RequestHeader("Authorization") String token,
                               @PathVariable("goodsChannelId") Integer goodsChannelId) throws Exception {
        return this.goodsChannelService.deleteGoodsChannel(token,goodsChannelId);
    }

    //规格选项管理
    //分页查找options数据
    @GetMapping("specs/options")
    Map<String,Object> findAllOptionByPage(@RequestHeader("Authorization") String token,
                                           @RequestParam("page") Integer page,
                                           @RequestParam("pagesize") Integer pageSize) throws Exception {
        return this.specificationOptionService.findAllOptionByPage(token, page, pageSize);
    }
    //添加保存options
    @PostMapping("specs/options")
    Integer saveOption(@RequestHeader("Authorization") String token,
                       SpecificationOption specificationOption) throws Exception {
        return this.specificationOptionService.saveOption(token, specificationOption);
    }
    //根据optionId获取option
    @GetMapping("specs/options/{optionId}")
    Map<String,Object> getOption(@RequestHeader("Authorization") String token,
                                 @PathVariable("optionId") Integer optionId) throws Exception {
        return this.specificationOptionService.getOption(token, optionId);
    }
    //根据optionsId修改option
    @PutMapping("specs/options/{optionId}")
    Integer updateOption(@RequestHeader("Authorization") String token,
                         @PathVariable("optionId") Integer optionId,
                         @RequestBody SpecificationOption specificationOption) throws Exception {
        return this.specificationOptionService.updateOption(token, optionId, specificationOption);
    }
    //根据optionsId删除option
    @DeleteMapping("specs/options/{optionId}")
    Integer delete(@RequestHeader("Authorization") String token,
                   @PathVariable("optionId") Integer optionId) throws Exception {
        return this.specificationOptionService.deleteOption(token, optionId);
    }

    //品牌管理
    //分页获取brand的数据
    @GetMapping("goods/brands")
    Map<String,Object> findAllBrandByPage(@RequestHeader("Authorization") String token,
                                          @RequestParam("page") Integer page,
                                          @RequestParam("pagesize") Integer pageSize) throws Exception {
        return this.brandService.findAllBrandByPage(token,page,pageSize);
    }

    //图片管理
    @GetMapping("skus/images")
    Map<String,Object> findAllSkuImage(@RequestHeader("Authorization") String token,
                                       @RequestParam("page") Integer page,
                                       @RequestParam("pagesize") Integer pageSize) throws Exception {
        return this.skuImageService.findAllSkuImageByPage(token,page,pageSize);
    }

    //SPU管理
    //分页查找spu数据
    @GetMapping("goods")
    Map<String,Object> findAllSpuByPage(@RequestHeader("Authorization") String token,
                                        @RequestParam("page") Integer page,
                                        @RequestParam("pagesize") Integer pageSize) throws Exception {
        return this.spuService.findAllSpuByPage(token, page, pageSize);
    }

}
