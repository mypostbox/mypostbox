package com.syedu.utils;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;
import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * author:Administrator
 * createTime:2023/6/1610:37
 */
public class RsaUtilsTest {



    @BeforeAll
    public static void doBeforeAll(){

    }

    @Test
    public void testGenerateKey() throws Exception {
        RsaUtils.generateKey("pub_key","pri_key","admin");
    }

    @Test
    public void testGetPublicKey() throws Exception {
        PublicKey pubKey = RsaUtils.getPublicKey("pub_key");
        System.out.println(pubKey);
    }

    @Test
    public void testGetPrivetKey() throws Exception {
        PrivateKey priKey = RsaUtils.getPrivateKey("pri_key");
        System.out.println(priKey);
    }


    @Test
    public void test(){
        int i  = 100;
        byte[] b = new byte[i];
       for(byte s : b){
           System.out.println(s);
       }

    }


}
