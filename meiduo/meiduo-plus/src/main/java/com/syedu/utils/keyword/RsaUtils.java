package com.syedu.utils.keyword;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.security.*;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;

// 使用代码来生成 公私钥  pair
// PublicKey  是一个接口
// 需要生成公钥和私钥字符串
// PrivateKey 是一个接口

/**
 * 工具类，就是生成 一对公私钥Java代码的写法
 * 1) 提供一个密匙 ，给你一对公私文件
 * 2） 给我一个文件，公钥文件 或私钥文件，生成公钥和私钥对象
 * <p>
 * 写到文件中去了
 * 公私钥是以文件的形式存储在服务器上
 */

public class RsaUtils {
    /**
     * 从文件中读取公钥
     *
     * @param filename 公钥保存路径，相对于classpath
     * @return 公钥对象
     * @throws Exception
     */
    public static PublicKey getPublicKey(String filename) throws Exception {
        // 测试类使用文件去读的
        Path path = new File(filename).toPath();
        String s = path.toString();
        byte[] bytes = Files.readAllBytes(path);
        return getPublicKey(bytes);
    }

    public static PublicKey getPublicKey1(String filename) throws Exception {
        // 类加载器去读取数据
        InputStream inputStream = RsaUtils.class.getClassLoader().getResourceAsStream(filename);
        int available = inputStream.available();
        byte[] bytes = new byte[available];
        inputStream.read(bytes);  //代码没有写
        return getPublicKey(bytes);
    }

    /**
     * 从文件中读取密钥
     *
     * @param filename 私钥保存路径，相对于classpath
     * @return 私钥对象
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(String filename) throws Exception {
        Path path = new File(filename).toPath();
        String s = path.toString();
        byte[] bytes = Files.readAllBytes(path);
        return getPrivateKey(bytes);
    }


    // 字节方式的读  一次读入到字节数组中去!!!
    public static PrivateKey getPrivateKey1(String filename) throws Exception {
        InputStream inputStream = RsaUtils.class.getClassLoader().getResourceAsStream(filename);
        int available = inputStream.available();
        byte[] bytes = new byte[available];
        inputStream.read(bytes);
        return getPrivateKey(bytes);
    }


    /**
     * 获取公钥
     *
     * @param bytes 公钥的字节形式
     * @return
     * @throws Exception
     */
    public static PublicKey getPublicKey(byte[] bytes) throws Exception {
        X509EncodedKeySpec spec = new X509EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePublic(spec);
    }

    /**
     * 获取密钥
     *
     * @param bytes 私钥的字节形式
     * @return
     * @throws Exception
     */
    public static PrivateKey getPrivateKey(byte[] bytes) throws Exception {
        PKCS8EncodedKeySpec spec = new PKCS8EncodedKeySpec(bytes);
        KeyFactory factory = KeyFactory.getInstance("RSA");
        return factory.generatePrivate(spec);
    }

    /**
     * 根据，生存rsa公钥和私钥,并写入指定文件  切口
     *
     * @param publicKeyFilename  公钥文件路径
     * @param privateKeyFilename 私钥文件路径
     * @param secret             生成密钥的密文  密匙
     * @throws IOException
     * @throws NoSuchAlgorithmException
     */
    public static void generateKey(String publicKeyFilename, String privateKeyFilename, String secret) throws Exception {
        //使用加密算法 RSA 生成公私钥的对儿
        KeyPairGenerator keyPairGenerator = KeyPairGenerator.getInstance("RSA"); //非对称加密 算法
        SecureRandom secureRandom = new SecureRandom(secret.getBytes()); //秘钥  ILOVEYOUVERYMUCH 切口 黑话 暗号
        keyPairGenerator.initialize(2048, secureRandom); // 长度 2048  破解难度
        KeyPair keyPair = keyPairGenerator.genKeyPair();
        // 获取公钥并写出
        byte[] publicKeyBytes = keyPair.getPublic().getEncoded();
        writeFile(publicKeyFilename, publicKeyBytes);
        // 获取私钥并写出
        byte[] privateKeyBytes = keyPair.getPrivate().getEncoded();
        writeFile(privateKeyFilename, privateKeyBytes);
    }


    // byte数组，写到本地文件
    private static void writeFile(String destPath, byte[] bytes) throws IOException {
        File dest = new File(destPath);
        if (!dest.exists()) {
            dest.createNewFile();
        }
        Files.write(dest.toPath(), bytes);
    }

    public static String acquireFile(String fileName) {
        URL resource = RsaUtils.class.getClassLoader().getResource(fileName);
        String path = resource.getPath();
        return path;
    }
}