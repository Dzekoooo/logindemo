package com.yijiupi.logindemo.shiro;

import org.apache.shiro.crypto.hash.Md5Hash;
import org.springframework.util.Assert;

/**
*@Author: ZhangZhe
*@Description MD5的重复加密
*@Date: 2017/12/23
*/
public class Md5EnCoder {
    public static final String DEFAULT_SALT = "##yjp##";
    public static final int DEFAULT_HASH_ITERATION = 3;

    public static String getMD5Hash(Object source) {
        return getMD5Hash(source, DEFAULT_SALT);
    }

    public static String getMD5Hash(Object source, Object salt) {
        return getMD5Hash(source, salt, DEFAULT_HASH_ITERATION);
    }

    /**
     * 进项md5 hash 重复安全加密
     *
     * @param source   加密对象
     * @param salt     盐值
     * @param hashIterations hash迭代次数
     * @return 加密后的32位密文
     */
    public static String getMD5Hash(Object source, Object salt, int hashIterations) {
        Assert.notEmpty(new Object[]{source, salt, hashIterations}, "");
        return new Md5Hash(source, salt, hashIterations).toString();
    }

    public static void main(String[] args) {
        System.out.println(getMD5Hash("test","test"));
    }
}
