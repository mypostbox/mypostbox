package com.syedu.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.syedu.domain.Address;
import com.syedu.service.AddressService;
import com.syedu.mapper.AddressMapper;
import org.springframework.stereotype.Service;

/**
* @author Administrator
* @description 针对表【tb_address】的数据库操作Service实现
* @createDate 2023-06-13 08:45:37
*/
@Service
public class AddressServiceImpl extends ServiceImpl<AddressMapper, Address>
    implements AddressService{

}




