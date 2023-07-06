package com.syedu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.conditions.query.LambdaQueryChainWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.*;
import com.syedu.mapper.GoodsVisitMapper;
import com.syedu.mapper.SkuSpecificationMapper;
import com.syedu.service.SkuService;
import com.syedu.mapper.SkuMapper;
import com.syedu.utils.util.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;
import redis.clients.jedis.Jedis;

import java.security.PublicKey;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;

/**
 * @author Administrator
 * @description 针对表【tb_sku】的数据库操作Service实现
 * @createDate 2023-06-13 08:45:37
 */
@Service
public class SkuServiceImpl extends ServiceImpl<SkuMapper, Sku>
        implements SkuService {

    @Autowired
    private Jedis jedis;
    @Autowired
    private PublicKey publicKey;
    @Autowired
    private SkuMapper skuMapper;
    @Autowired
    private GoodsVisitMapper goodsVisitMapper;
    @Autowired
    private SkuSpecificationMapper skuSpecificationMapper;

    /**
     * 查找购物车商品
     *
     * @param token
     * @return
     * @throws Exception
     */
    @Override
    public List<Sku> findCart(String token) throws Exception {
        List<Sku> list = new ArrayList<>();
        Users user = JwtUtils.getInfoFromToken(token, publicKey);
        String key = "cart" + user.getId();
        if(this.jedis.exists(key)) {
            System.out.println(this.jedis.type(key));
            Set<String> fields = this.jedis.hkeys(key);
            for (String field : fields) {
                Sku sku = this.skuMapper.selectById(Integer.parseInt(field));
                String hget = this.jedis.hget(key, field);
                String[] split = hget.split(":");
                Integer count = Integer.parseInt(split[0]);
                boolean selected = Boolean.parseBoolean(split[1]);
                sku.setCount(count);
                sku.setSelected(selected);
                list.add(sku);
            }
            this.jedis.close();
        }
        return list;
    }

    /**
     * 删除购物车商品
     */
    @Override
    public Integer delCart(String token, Map<String,Integer> map) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        this.jedis.hdel("cart"+user.getId(),map.get("sku_id").toString());
        this.jedis.close();
        return 200;
    }

    /**
     * 更新购物车的商品
     * @param token
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public Sku updateCart(String token, Map<String, Object> map) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        Integer skuId = (Integer) map.get("sku_id");
        Integer count = (Integer) map.get("count");
        boolean selected = (Boolean) map.get("selected");
        Sku sku = this.skuMapper.selectById(skuId);
        sku.setSelected(selected);
        sku.setCount(count);
        this.jedis.hset("cart" + user.getId(), skuId.toString(), count + ":" + selected);
        this.jedis.close();
        return sku;
    }

    /**
     * 添加购物车
     * @param token
     * @param map
     * @return
     * @throws Exception
     */
    @Override
    public Integer addCart(String token, Map<String, Object> map) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        Integer skuId = (Integer) map.get("sku_id");
        Integer count = (Integer) map.get("count");
        boolean selected = (Boolean) map.get("selected");
        if(this.jedis.hexists("cart"+user.getId(),skuId.toString())){
            String hget = this.jedis.hget("cart" + user.getId(), skuId.toString());
            String[] split = hget.split(":");
            count += Integer.parseInt(split[0]);
            this.jedis.hset("cart" + user.getId(), skuId.toString(), count + ":" + selected);
        }else{
            this.jedis.hset("cart" + user.getId(), skuId.toString(), count + ":" + selected);
        }
        this.jedis.close();
        return 200;
    }


    @Override
    public Boolean selectedAll(String token, Map<String, Object> map) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        Boolean selected = (Boolean) map.get("selected");
        String key = "cart" + user.getId();
        Set<String> hkeys = this.jedis.hkeys(key);
        for(String s : hkeys) {
            String hget = this.jedis.hget(key, s);
            String[] split = hget.split(":");
            this.jedis.hset(key,s,split[0]+":" +selected);
        }
        this.jedis.close();
        return selected;
    }

    /**
     * 根据skuId，查商品详细信息
     *
     * @param id
     * @return
     */
    @Override
    public Sku findSkuDetailById(Integer id) {
        return this.skuMapper.selectById(id);
    }

    @Override
    public Map<String, Object> listService(Integer cat,
                                           Integer page,
                                           Integer pageSize,
                                           String ordering) {
        HashMap<String, Object> map = new HashMap<>();
        Page<Sku> skuPage = new Page<>(page, pageSize);
        LambdaQueryWrapper<Sku> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(Sku::getCategoryId, cat);
        switch (ordering) {
            case "-create_time":
                wrapper.orderByDesc(Sku::getCreateTime);
                break;
            case "create_time":
                wrapper.orderByAsc(Sku::getCreateTime);
                break;
        }
        this.skuMapper.selectPage(skuPage, wrapper);
        map.put("count", skuPage.getTotal());
        map.put("results", skuPage.getRecords());
        return map;
    }

    //获取热销数据
    @Override
    public List<Sku> fundHot() {
        Page<Sku> skuPage = new Page<>(1, 2);
        LambdaQueryWrapper<Sku> wrapper = new LambdaQueryWrapper<>();
        wrapper.orderByDesc(Sku::getSales);
        this.skuMapper.selectPage(skuPage, wrapper);
        return skuPage.getRecords();
    }

    //添加用户浏览记录
    @Override
    public void browseHistories1(Integer skuId, String token) throws Exception {
        Users users = JwtUtils.getInfoFromToken(token, this.publicKey);
        String user = "user" + users.getId();
        if(this.jedis.llen(user) > 10){
            this.jedis.lpush(user,skuId.toString());
            this.jedis.rpop(user);
        }else{
            this.jedis.lpush(user,skuId.toString());
        }
        insertVisit(skuId);
    }

    /**
     * 将商品浏览记录存入tb_goods_visit表
     * @param skuId
     * @throws ParseException
     */
    private void insertVisit(Integer skuId) throws ParseException {
        Sku sku = this.skuMapper.selectById(skuId);
        String currentDate = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now());
        LambdaQueryWrapper<GoodsVisit> wrapper = new LambdaQueryWrapper<>();
        wrapper.eq(GoodsVisit::getCategoryId,sku.getCategoryId());
        if(this.goodsVisitMapper.exists(wrapper)){
            GoodsVisit goodsVisit = this.goodsVisitMapper.selectOne(wrapper);
            if(goodsVisit.getDate().toString().equals(currentDate)){
                goodsVisit.setCount(goodsVisit.getCount()+1);
                this.goodsVisitMapper.updateById(goodsVisit);
            }else{
                goodsVisit.setDate(LocalDate.parse(currentDate));
                goodsVisit.setCount(1);
                this.goodsVisitMapper.updateById(goodsVisit);
            }
        }else{
           this.goodsVisitMapper.InsertGoodsVisit(1,sku.getCategoryId());
        }


    }
    //获取用户浏览记录
    @Override
    public List<Sku> browseHistories2(String token) throws Exception {
        List<Sku> list = new ArrayList<>();
        Users user = JwtUtils.getInfoFromToken(token,this.publicKey);
        List<String> lrange = this.jedis.lrange("user" + user.getId(), 0, -1);
        for(String s : lrange){
            list.add(this.skuMapper.selectById(Integer.parseInt(s)));
        }
        return list;
    }

    //获取用户的结算商品
    @Override
    public Map<String, Object> findAllSku(String token) throws Exception {
        Map<String, Object> map = new HashMap<>();
        List<Sku> skus = new ArrayList<>();
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        String key = "cart" + user.getId();
        if(this.jedis.exists(key)){
            Set<String> hkeys = this.jedis.hkeys(key);
            for(String s : hkeys){
                String hget = this.jedis.hget(key, s);
                String[] split = hget.split(":");
                Integer count = Integer.valueOf(split[0]);
                boolean selected = Boolean.parseBoolean(split[1]);
                if(selected){
                    Sku sku = this.skuMapper.selectById(Integer.parseInt(s));
                    sku.setSelected(selected);
                    sku.setCount(count);
                    skus.add(sku);
                }
            }
        }
        map.put("skus",skus);
        if(skus.size()<10){
            map.put("freight",50);
        }else{
            map.put("freight",10);
        }
        return map;
    }
    //分页获取所有的商品sku(或根据关键字查找)
    @Override
    public Map<String, Object> findAllSkuByPage(String token, Integer page, Integer pageSize, String keyword) throws Exception {
        Map<String,Object> map = new HashMap<>();
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            Page<Sku> skuPage = new Page<>(page,pageSize);
            if(keyword == null || "".equals(keyword)){
                this.skuMapper.selectPage(skuPage,null);
                map.put("lists",skuPage.getRecords());
                map.put("page",page);
                map.put("pages", Math.ceil(Double.parseDouble(Long.toString(skuPage.getTotal())) / Double.parseDouble(pageSize.toString())));
            }else{
                LambdaQueryWrapper<Sku> wrapper = new LambdaQueryWrapper<>();
                wrapper.like(Sku::getName,"%"+keyword+"%");
                this.skuMapper.selectPage(skuPage,wrapper);
                map.put("lists",skuPage.getRecords());
                map.put("page",page);
                map.put("pages", Math.ceil(Double.parseDouble(Long.toString(skuPage.getTotal())) / Double.parseDouble(pageSize.toString())));
            }
            return map;
        }
        return null;
    }

    @Override
    @Transactional
    public Sku saveSkuAndSkuSpecification(String token, Sku skus) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            System.out.println(skus);
            this.skuMapper.insert(skus);
            Sku sku = this.skuMapper.selectOne(new LambdaQueryWrapper<Sku>().eq(Sku::getName, skus.getName()));
            for (Map<String, Object> skuSpecification : skus.getSpecs()) {
                Object optionId = skuSpecification.get("optionId");
                Object specId = skuSpecification.get("specId");
                this.skuSpecificationMapper.insert(new SkuSpecification().setSkuId(sku.getId()).setSpecId(Integer.parseInt(specId.toString())).setOptionId(Integer.parseInt(optionId.toString())));
            }
        }
        return null;
    }

    @Override
    public Sku findSku(String token, Integer skuId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            return this.skuMapper.findSkuWithSkuSpecification(skuId);
        }
        return null;
    }
    //更新sku信息
    @Override
    @Transactional
    public Sku updateSku(String token, Integer skuId, Sku sku) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            sku.setId(skuId);
            System.out.println(sku);
           this.skuMapper.updateById(sku);
           this.skuSpecificationMapper.delete(new LambdaQueryWrapper<SkuSpecification>().eq(SkuSpecification::getSkuId,skuId));
            List<Map<String, Object>> specs = sku.getSpecs();
            for(Map<String,Object> option : specs){
                Object specId = option.get("spec_id");
                Object optionId = option.get("option_id");
                this.skuSpecificationMapper.insert(new SkuSpecification().setSkuId(skuId).setSpecId(Integer.parseInt(specId.toString())).setOptionId(Integer.parseInt(optionId.toString())));
            }
            return sku;
        }
        return null;
    }
    //删除sku
    @Override
    @Transactional
    public Integer deleteSku(String token, Integer skuId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            this.skuSpecificationMapper.delete(new LambdaQueryWrapper<SkuSpecification>().eq(SkuSpecification::getSkuId,skuId));
            this.skuMapper.deleteById(skuId);
            return 200;
        }
        return null;
    }
    //获取所有sku的种类
    @Override
    public List<Sku> findAllSkus(String token) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
            return this.skuMapper.selectList(null);
        }
        return null;
    }

}




