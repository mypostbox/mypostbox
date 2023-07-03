package com.syedu.mapper;

import com.syedu.domain.Address;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

import java.util.List;

/**
 * author:Administrator
 * createTime:2023/7/216:00
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class AddressMapperTest {
    @Autowired
    private AddressMapper addressMapper;

    @Test
    public void testFindAllAddressByUserId(){
        List<Address> allAddressByUserId = this.addressMapper.findAllAddressByUserId(1);
        allAddressByUserId.stream().forEach(System.out::println);
    }
}
