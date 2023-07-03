package com.syedu.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.LambdaQueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.Address;
import com.syedu.domain.Users;
import com.syedu.mapper.UsersMapper;
import com.syedu.service.AddressService;
import com.syedu.mapper.AddressMapper;
import com.syedu.utils.util.JwtUtils;
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
        List<Address> addresses = this.addressMapper.findAllAddressByUserId(user.getId());
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

    /**
     * 保存用户地址信息
     * @param token
     * @param address
     * @return
     */
    @Override
    public Address saveAddress(String token,Address address) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        address.setUserId(user.getId());
        this.addressMapper.insert(address);
        return address;
    }

    /**
     * 用户地址修改
     * @param token
     * @param address
     * @param addressId
     * @return
     */
    @Override
    public Address updateAddress(String token, Address address, Integer addressId) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null){
           address.setId(addressId);
           this.addressMapper.updateById(address);
           return address;
        }
        return null;
    }

    /**
     * 修改保存地址title
     * @param token
     * @param addressId
     * @return
     */
    @Override
    public Address updateAddressTitle(String token, Integer addressId,String title) throws Exception {
        Users user = JwtUtils.getInfoFromToken(token, this.publicKey);
        if(user.getId() != null) {
            Address address = this.addressMapper.selectById(addressId);
            address.setTitle(title);
            this.addressMapper.updateById(address);
            return address;
        }
        return null;
    }


}




