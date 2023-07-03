package com.syedu.service;

import com.syedu.domain.Address;
import com.syedu.utils.config.SpringConfiguration;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringJUnitConfig;

/**
 * author:Administrator
 * createTime:2023/7/215:14
 */
@SpringJUnitConfig(classes = {SpringConfiguration.class})
public class AddressServiceTest {

    @Autowired
    private AddressService addressService;


    @Test
    public void test(){
        System.out.println(addressService);
    }

    @Test
    public void testSaveAddress() throws Exception {
        String token = "eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJ0b20iLCJwYXNzd29yZCI6IjExMTExMTExIiwiZW1haWwiOiI5ODk5OUBxcS5jb20iLCJleHAiOjE2ODg4ODM3MjR9.a-KBREjXLd2rESqotbI1DQjkzGq6TVyrAflNwQQwRNnm94qkq6MukO4zED53ZtNb6QOEZg2HXybzyThAlBNN7O8Bm5vSLkp3wGfpFksKNPQ0YgtRghTZjlYFLbvyYnyWaLbLVqad5dNyo5nBCb3v-zFI61423vzvTjpp3opZwSMJqo5EluoqFMRh2-c58f086IVZ-dve44-WXu_ZU2ug-NUU8uptwbUPpZDARjzgH3G1lNFdiggLRBMQkeBuwDHNCJX_iTbQhXwFA5lu2umXDPphSkX8Y6a5b9KxmnUTSd5yGWhbhf0BQYpztpl22uX3xsnpSMNlCyoC3TUqpZaCzw";
        Address address = new Address();

        address.setUserId(1).setReceiver("lily").setProvinceId(360000).setCityId(361100).setDistrictId(361128).setPlace("山中").setMobile("18838338835").setTel("110").setEmail("lily@qq.com").setTitle("lily");
        Address address1 = this.addressService.saveAddress(token, address);
    }

    @Test
    public void testUpdateAddressTitle() throws Exception {
        Address address = this.addressService.updateAddressTitle("df", 1, "jev");
        System.out.println(address);
    }
}
