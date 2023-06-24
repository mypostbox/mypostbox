package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
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
     * @param token
     * @return
     * @throws Exception
     */
    @Override
    public List<Sku> findCart(String token) throws Exception {
        List<Sku> list = new ArrayList<>();
        Users user = JwtUtils.getInfoFromToken(token, publicKey);
        Set<String> fields = this.jedis.hkeys("cart" + user.getId());
        for (String field : fields) {
            Sku sku = this.skuMapper.selectById(field);
            String count = this.jedis.hget("cart" + user.getId(), field);
            sku.setCount(Integer.parseInt(count));
            list.add(sku);
        }
        return list;
    }

    /**
     * 删除购物车商品
     */
    @Override
    public Integer delCart(String token, String skuId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        long hdel = this.jedis.hdel("cart" + user.getId(), skuId);
        System.out.println(hdel);
        return 200;
    }

    /**
     * 更新购物车的商品
     * @param token
     * @param skuId
     * @param count
     * @return
     */
    @Override
    public Integer updateCart(String token, String skuId, String count) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        this.jedis.hset("cart"+user.getId(),skuId,count);
        return Integer.parseInt(count);
    }

    /**
     * 根据skuId，查商品详细信息
     * @param id
     * @return
     */
    @Override
    public Sku findSkuDetailById(Integer id) {
        Sku sku = this.skuMapper.selectById(id);


        return sku;
    }


}




