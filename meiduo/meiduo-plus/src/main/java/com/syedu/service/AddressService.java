package com.syedu.service;

import com.syedu.domain.Address;
import com.baomidou.mybatisplus.extension.service.IService;

import java.util.Map;

/**
* @author Administrator
* @description 针对表【tb_address】的数据库操作Service
* @createDate 2023-06-13 08:45:37
*/
public interface AddressService extends IService<Address> {

    Map<String,Object> findAllAddress(String token) throws Exception;

    Integer addressStatus(String token,Integer addressId) throws Exception;

    Address saveAddress(String token,Address address) throws Exception;
    Address updateAddress(String token,Address address,Integer addressId) throws Exception;
    Address updateAddressTitle(String token,Integer addressId,String title) throws Exception;
}
