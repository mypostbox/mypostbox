package com.syedu.service.impl;


import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.gson.Gson;
import com.syedu.domain.Sku;
import com.syedu.domain.Users;
import com.syedu.service.SkuService;
import com.syedu.mapper.SkuMapper;
import com.syedu.utils.keyword.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import redis.clients.jedis.Jedis;

import java.security.PublicKey;
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
}




