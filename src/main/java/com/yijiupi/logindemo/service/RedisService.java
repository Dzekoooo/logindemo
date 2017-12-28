package com.yijiupi.logindemo.service;

/**
*@Author: ZhangZhe
*@Description
*@Date: 2017/12/25
*/

public interface RedisService {

    /**
     * 存放key
     * 
     * @param key
     * @param value
     */
    void set(String key,String value);

    /**
     * 得到key
     * 
     * @param key
     * @return
     */
    String get(String key);
}
