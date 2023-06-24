package com.syedu.utils;

import com.syedu.domain.Users;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.io.File;
import java.io.IOException;
import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.PrivateKey;
import java.security.PublicKey;
import java.util.List;

/**
 * author:Administrator
 * createTime:2023/6/1610:36
 */
public class JwtUtilsTest {

    private static PublicKey pubKey;
    private static PrivateKey priKey;
    private static Users user;
    @BeforeAll
    public static void doBeforeAll() throws Exception {

        user = new Users();
        user.setUsername("lily").setPassword("1234").setEmail("lili@qq.com").setId(1);
    }

    @Test
    public void test10(){
        System.out.println(pubKey);
    }

    @Test
    public void test() throws Exception {
        String s = JwtUtils.generateToken(user, priKey, 60 * 24 * 360 * 100);
        System.out.println(s);
    }


    //eyJhbGciOiJSUzI1NiJ9.eyJ1c2VybmFtZSI6ImxpbHkiLCJwYXNzd29yZCI6IjEyMzQiLCJlbWFpbCI6ImxpbGlAcXEuY29tIiwiZXhwIjo0Nzk3Mjg0MTIyfQ.UwQdEhVHvfKOiaFQnL3yWevGlqi0qQg_VItKXYX8U6FzC7rla-1dVnkokK6J7h-PT96Im-IS810wR963rBZczliYIXXNga3paYuUE9ZtiMTB6fm3kpKjBF3u-gBk8DnWxcAsk7wpEjkYiWpuZheHKVmC_hwZHyI-wfY63MbyFM3F42z_8NJ_AVIcBxgteO0msyBiJFaS0RVFZo-7o4sS_uWFVUpIlndQOzd9Mpplna_4sDut0IEdTHJIcjCGDiN8N5lF7Vibuna6IgVGgCk2aTTsNMJr3UWLRNqtL0TV3L0cqI6nObREcCbodtdeLg3BE98S8wtCDVPXKjoD05xqiw
    @Test
    public void test1() throws Exception {
        Users infoFromToken = JwtUtils.getInfoFromToken("eyJhbGciOiJSUzI1NiJ9.eyJpZCI6MSwidXNlcm5hbWUiOiJsaWx5IiwicGFzc3dvcmQiOiIxMjM0IiwiZW1haWwiOiJsaWxpQHFxLmNvbSIsImV4cCI6NDc5NzI4NDMwMH0.fVTBQoEmQdKPMDUt0FJ6unjO_J24KymdMfVtaCY_apt-gQb5ZOYuXd1l8XsbgUWJ5iAgbaWyx2EI-M_sJpcnYwUHdQc1Sj2c8mL5vx7YvI-lGX1sV3qvwwuBB6DCmT-ItQxPLouuoH1UrVXD7-wz9-_rcYyXkCE0GI9n1f48S72nCrYgJGFtVqLMRjZZDML4E7h3ThsH_qLCzDLnh1sTNA5OlUXHi1EbGu6-THKluPq1A9wYg9NRHuQuw5zUdISpagE6H8Y_5lOPgrS1DnnkYzouDOXL_ciq0tUch3B5Klfz_hWeo9txQ-lWTiafp8YZrwKVv3kICXI9BcBnPxMCOw", pubKey);
        System.out.println(infoFromToken);
    }





}
