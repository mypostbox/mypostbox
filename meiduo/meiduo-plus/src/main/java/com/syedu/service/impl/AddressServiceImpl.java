package com.syedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.Address;
import com.syedu.domain.Users;
import com.syedu.mapper.UsersMapper;
import com.syedu.service.AddressService;
import com.syedu.mapper.AddressMapper;
import com.syedu.utils.keyword.JwtUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.PublicKey;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_address】的数据库操作Service实现
* @createDate 2023-06-13 08:45:37
*/
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
    implements AddressService{

    @Autowired
    private PublicKey publicKey;
    @Autowired
    private AddressMapper addressMapper;
    @Autowired
    private UsersMapper usersMapper;

    @Override
    public Map<String, Object> findAllAddress(String token) throws Exception {
        Map<String,Object> map = new HashMap<>();
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        Users users = this.usersMapper.selectById(user.getId());
        LambdaQueryWrapper<Address> wrapper = new LambdaQueryWrapper();
        wrapper.eq(Address::getUserId,users.getId());
        List<Address> addresses = this.addressMapper.selectList(wrapper);
        map.put("addresses",addresses);
        map.put("default_address_id",users.getDefaultAddressId());
        map.put("limit",8);
        return map;
    }

    @Override
    public Integer addressStatus(String token, Integer addressId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        Users users = this.usersMapper.selectById(user.getId());
        users.setDefaultAddressId(addressId);
        return this.usersMapper.updateById(users);
    }


}




