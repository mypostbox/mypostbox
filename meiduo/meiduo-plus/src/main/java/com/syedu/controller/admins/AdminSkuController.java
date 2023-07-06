package com.syedu.controller.admins;


import com.syedu.domain.*;
import com.syedu.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;;
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
                @RequestBody Sku sku) throws Exception {
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
                        @RequestBody SpuSpecification spuSpecification) throws Exception {
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
                             @RequestBody GoodsChannel goodsChannel) throws Exception {
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
                       @RequestBody SpecificationOption specificationOption) throws Exception {
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
    //添加品牌
    @PostMapping("goods/brands")
    Integer saveBrand(@RequestHeader("Authorization") String token,
                      @RequestParam("name") String name,
                      @RequestParam("first_letter") String first_letter,
                      @RequestParam("logo") MultipartFile logo) throws Exception {
        return this.brandService.saveBrand(token, name, first_letter, logo);
    }
    //获取品牌信息
    @GetMapping("goods/brands/{brandId}")
    Map<String,Object> findBrand(@RequestHeader("Authorization") String token,
                                 @PathVariable("brandId") Integer brandId) throws Exception {
        return this.brandService.findBrand(token, brandId);
    }
    //修改品牌信息
    @PostMapping("goods/brands/{brandId}")
    Integer updateBrand(@RequestHeader("Authorization") String token,
                        @PathVariable("brandId") Integer brandId,
                        @RequestParam("name") String name,
                        @RequestParam("first_letter") String first_letter,
                        @RequestParam("logo") MultipartFile logo) throws Exception {
        return this.brandService.updateBrand(token, brandId, name, first_letter, logo);
    }
    //删除品牌
    @DeleteMapping("goods/brands/{brandId}")
    Integer deleteBrand(@RequestHeader("Authorization") String token,
                        @PathVariable("brandId") Integer brandId) throws Exception {
        return this.brandService.deleteBrand(token,brandId);
    }

    //图片管理
    @GetMapping("skus/images")
    Map<String,Object> findAllSkuImage(@RequestHeader("Authorization") String token,
                                       @RequestParam("page") Integer page,
                                       @RequestParam("pagesize") Integer pageSize) throws Exception {
        return this.skuImageService.findAllSkuImageByPage(token,page,pageSize);
    }
    //获取所有sku的种类
    @GetMapping("skus/simple")
    List<Sku> findAllSku(@RequestHeader("Authorization") String token) throws Exception {
        return this.skuService.findAllSkus(token);
    }
    //保存图片
    @PostMapping("skus/images")
    Integer saveImage(@RequestHeader("Authorization") String token,
                      @RequestParam("sku") Integer skuId,
                      @RequestParam("image") MultipartFile image) throws Exception {;
        return this.skuImageService.saveImage(token, skuId, image);
    }
    //根据id获取图片详细信息
    @GetMapping("skus/images/{imagesId}")
    SkuImage findImage(@RequestHeader("Authorization") String token,
                                 @PathVariable("imagesId") Integer imagesId) throws Exception {
        return this.skuImageService.findImage(token, imagesId);
    }
    //修改照片
    @PostMapping("skus/images/{imagesId}")
    Integer updateImage(@RequestHeader("Authorization") String token,
                        @PathVariable("imagesId") Integer imagesId,
                        @RequestParam("sku") Integer sku,
                        @RequestParam("image") MultipartFile image) throws Exception {
        return this.skuImageService.updateImage(token, imagesId, sku, image);
    }
    //删除照片
    @DeleteMapping("skus/images/{imagesId}")
    Integer deleteImage(@RequestHeader("Authorization") String token,
                        @PathVariable("imagesId") Integer imagesId) throws Exception {
        return this.skuImageService.deleteImage(token,imagesId);
    }

    //SPU管理
    //分页查找spu数据
    @GetMapping("goods")
    Map<String,Object> findAllSpuByPage(@RequestHeader("Authorization") String token,
                                        @RequestParam("page") Integer page,
                                        @RequestParam("pagesize") Integer pageSize) throws Exception {
        return this.spuService.findAllSpuByPage(token, page, pageSize);
    }
    //获取一级商品类型
    @GetMapping("goods/channel/categories")
    List<GoodsCategory> findChannelCategories1(@RequestHeader("Authorization") String token) throws Exception {
        return this.goodsCategoryService.findGoodsCategoryOne(token);
    }
    //获取子级商品类型
    @GetMapping("goods/channel/categories/{categoryId}")
    List<GoodsCategory> findChannelCategoriesChild(@RequestHeader("Authorization") String token,
                                                   @PathVariable("categoryId") Integer categoryId) throws Exception {
        return this.goodsCategoryService.findGoodsCategoryChild(token, categoryId);
    }
    //获取品牌的类型
    @GetMapping("goods/brands/simple")
    List<Brand> findAllBrand(@RequestHeader("Authorization") String token) throws Exception {
        return this.brandService.findAllBrand(token);
    }
    //根据id获取spu信息
    @GetMapping("goods/{spuId}")
    Map<String,Object> findSpu(@RequestHeader("Authorization") String token,
                               @PathVariable("spuId") Integer spuId) throws Exception {
        return this.spuService.findSpuById(token,spuId);
    }
    //接收照片
    @PostMapping("goods/images")
    Map<String,Object> saveImage(@RequestPart MultipartFile image,
                                 @RequestHeader("Authorization") String token) throws Exception {
       return this.skuImageService.saveImage(token,image);
    }

//            System.out.println(image);
//
//            String path = "http://localhost:9090/upload/";
//            String paths = "D:/nginx/nginx-1.18.0/nginx-1.18.0/image";

            //image.transferTo(new File(path,filename));
//        //跨服务器
//        String targetURL = "http://localhost:9090/upload/";
//        URL url = new URL(targetURL);
//        HttpURLConnection connection = (HttpURLConnection) url.openConnection();
//        // 设置请求方法为POST
//        connection.setRequestMethod("POST");
//        // 允许输入输出流
//        connection.setDoInput(true);
//        connection.setDoOutput(true);
//        // 设置请求头内容（根据需要进行适当设置）
//        connection.setRequestProperty("Content-Type", "multipart/form-data; boundary=---BOUNDARY");
//        DataOutputStream dataOutputStream = new DataOutputStream(connection.getOutputStream());
//        dataOutputStream.write(image.getBytes());
//        // 关闭流
//        dataOutputStream.flush();
//        dataOutputStream.close();
//        // 获取响应码
//        int responseCode = connection.getResponseCode();
//        if (responseCode == HttpURLConnection.HTTP_OK) {
//            // 文件上传成功
//            System.out.println("File uploaded successfully.");
//        } else {
//            // 文件上传失败
//            System.out.println("File upload failed. Response Code: " + responseCode);
//        }

        // C:\Users\Administrator\Desktop\美多\meiduo_admin\static\images
        //String uploadPath = request.getSession().getServletContext().getRealPath("img");
        // image.transferTo(new File(uploadPath,filename));

//        String filename = image.getOriginalFilename();
//        filename = UUID.randomUUID().toString().replace("-","") + filename ;
//
//        Client client = Client.create();
//        WebResource resource = client.resource(path + filename);
//        resource.post(image.getBytes());
    //保存spu
    @PostMapping("goods")
    Integer saveSpu(@RequestHeader("Authorization") String token,
                    @RequestBody Spu spu) throws Exception {
        return this.spuService.saveSpu(token, spu);
    }
    //修改spu
    @PutMapping("goods/{spuId}")
    Integer updateSpu(@RequestHeader("Authorization") String token,
                      @PathVariable("spuId") Integer spuId,
                      @RequestBody Spu spu) throws Exception {
        return this.spuService.updateSpu(token, spuId, spu);
    }
    //删除spu
    @DeleteMapping("goods/{spuId}")
    Integer deleteSpu(@RequestHeader("Authorization") String token,
                      @PathVariable("spuId") Integer spuId) throws Exception {
        return this.spuService.deleteSpu(token, spuId);
    }

}
