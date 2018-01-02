package com.yijiupi.logindemo.service.impl;

import com.yijiupi.logindemo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.stereotype.Service;

/**
*@Author: ZhangZhe
*@Description RedisService 实现类
*@Date: 2017/12/25
*/

@Service
public class RedisServiceImpl implements RedisService {

    @Autowired
    private RedisTemplate<String, String> redisTemplate;

    /**
     * key-value 设置缓存
     *
     * @param key
     * @param value
     */
    @Override
    public void set(String key, String value) {
        ValueOperations<String, String> vo = redisTemplate.opsForValue();
        vo.set(key, value);
    }

    /**
     * 通过key 读取缓存
     *
     * @param key
     * @return
     */
    @Override
    public String get(String key) {
        ValueOperations<String, String> vo = redisTemplate.opsForValue();
        return vo.get(key);
    }
}
