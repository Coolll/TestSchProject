package com.wql.utils.security;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONObject;
import com.wql.utils.publicUtils.PublicUtil;
import org.bouncycastle.jce.provider.BouncyCastleProvider;

import org.bouncycastle.jce.provider.BouncyCastleProvider;
import org.bouncycastle.util.encoders.Hex;

import javax.crypto.Cipher;
import javax.crypto.NoSuchPaddingException;
import javax.crypto.spec.IvParameterSpec;
import javax.crypto.spec.SecretKeySpec;
import java.security.Key;
import java.security.NoSuchAlgorithmException;
import java.security.NoSuchProviderException;
import java.security.Security;
import java.util.Arrays;
import java.util.Base64;

public class AESUtil {
    /**
     *
     * @author ngh
     * AES128 算法
     *
     * CBC 模式
     *
     * PKCS7Padding 填充模式
     *
     * CBC模式需要添加偏移量参数iv，必须16位
     * 密钥 sessionKey，必须16位
     *
     * 介于java 不支持PKCS7Padding，只支持PKCS5Padding 但是PKCS7Padding 和 PKCS5Padding 没有什么区别
     * 要实现在java端用PKCS7Padding填充，需要用到bouncycastle组件来实现
     */
    private final String sessionKey = "1234567812345678";
    // 偏移量 16位
    private final String iv = "8765432187654321";

    // 算法名称
    final String KEY_ALGORITHM = "AES";
    // 加解密算法/模式/填充方式
    final String algorithmStr = "AES/CBC/PKCS7Padding";
    // 加解密 密钥 16位

    byte[] ivByte;
    byte[] keybytes;
    private Key key;
    private Cipher cipher;
    boolean isInited = false;

    public void init() {
        // 如果密钥不足16位，那么就补足.  这个if 中的内容很重要
        keybytes = sessionKey.getBytes();
        ivByte = iv.getBytes();

        // 初始化
        Security.addProvider(new BouncyCastleProvider());
        // 转化成JAVA的密钥格式
        key = new SecretKeySpec(keybytes, KEY_ALGORITHM);
        try {
            // 初始化cipher
            cipher = Cipher.getInstance(algorithmStr, "BC");
        } catch (NoSuchAlgorithmException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchPaddingException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (NoSuchProviderException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
    }
    /**
     * 加密方法
     *
     * @param content 要加密的字符串
     * @return
     */
    public String encrypt(String content) {
        byte[] encryptedText = null;
        byte[] contentByte = content.getBytes();
        init();
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(ivByte));
            encryptedText = cipher.doFinal(contentByte);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new String(Hex.encode(encryptedText));
    }
    /**
     * 加密方法
     *
     * @param content 要加密的字符串
     * @return
     */
    public String encryptOrigin(String content) {
        byte[] encryptedText = null;
        byte[] contentByte = content.getBytes();
        init();
        try {
            cipher.init(Cipher.ENCRYPT_MODE, key, new IvParameterSpec(ivByte));
            encryptedText = cipher.doFinal(contentByte);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new String(encryptedText);
    }
    /**
     * 解密方法
     *
     * @param encryptedData 要解密的字符串
     * @return
     */
    public String decrypt(String encryptedData) {
        byte[] encryptedText = null;

        byte[] encrypted = Base64.getDecoder().decode(encryptedData);
        byte[] encryptedDataByte = Hex.decode(encrypted);
        init();

        try {
            cipher.init(Cipher.DECRYPT_MODE, key, new IvParameterSpec(ivByte));
            encryptedText = cipher.doFinal(encryptedDataByte);
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return new String(encryptedText);
    }

    public static void main(String[] args) {

        Integer currentTime = PublicUtil.loadBeijingTimeSeconds();
        System.out.println("当前时间戳为："+currentTime);

        Integer timestamp = PublicUtil.loadTodayZeroStamp();
        System.out.println("今日00：00时间戳为："+timestamp);

        AESUtil aes = new AESUtil();

        //加密字符串
        String content = "变啦智造";
        System.out.println("加密前的：" + content);

        // 加密方法
        String enc = aes.encrypt(content);

        byte[] encodeByte = Base64.getEncoder().encode(enc.getBytes());
        String encodeString = new String(encodeByte);
        System.out.println("加密后的内容：" + encodeString);

        // 解密方法
        String dec = aes.decrypt(encodeString);
        System.out.println("解密后的内容：" + dec);


        String str = "{\"code\":\"043P7DuF1xUDb70XS3wF101VuF1P7DuR\",\"iv\":\"D/2y4bNem01ME3ogFhuGMA==\",\"temp_user_id\":\"10000006\",\"app_id\":\"wxb7c0b228d52073eb\"}";
        JSONObject object = JSON.parseObject(str);
//        LoginParam param = JSONObject.toJavaObject(object,LoginParam.class);
//        LoginParam param = JSON.parseObject(str,LoginParam.class);
//        System.out.println( "param:"+param);




    }

}
