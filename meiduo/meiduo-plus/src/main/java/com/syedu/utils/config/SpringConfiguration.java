package com.syedu.utils.config;


import com.syedu.utils.util.RsaUtils;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Import;

import java.security.PrivateKey;
import java.security.PublicKey;

/**
 * author:Administrator
 * createTime:2023/6/1515:22
 */
@Import({MybatisConfig.class, TransactionConfig.class,ComponentConfig.class,AspectConfig.class})
@Configuration
@ComponentScan(basePackages = {"com.syedu.service"})
public class SpringConfiguration {
    @Bean
    public PublicKey publicKey() throws Exception {
        return RsaUtils.getPublicKey1("pub_key");
    }
    @Bean
    public PrivateKey privateKey() throws Exception {
        return RsaUtils.getPrivateKey1("pri_key");
    }

//    @Bean
//    public AlipayConfig alipayConfig(){
//        AlipayConfig alipayConfig = new AlipayConfig();
//        alipayConfig.setAppId("2021004103611012");
//        alipayConfig.setPrivateKey();
//        alipayConfig.setSignType();
//        alipayConfig.set
//    }

//    @Bean
//    public DefaultAlipayClient defaultAlipayClient(AlipayConfig alipayConfig){
//        DefaultAlipayClient.Builder builder = new DefaultAlipayClient.Builder();
//        DefaultAlipayClient build = builder.build();
//        Signer signer = build.getSigner();
//        signer.sign()
//    }



}
