package com.yijiupi.logindemo.dao;

import com.yijiupi.logindemo.pojo.UserPO;
import org.springframework.cache.annotation.CacheConfig;
import org.springframework.stereotype.Repository;

/**
*@Author: ZhangZhe
*@Description Mapper接口
*@Date: 2017/12/15
*/

@Repository
@CacheConfig(cacheNames = "users")
public interface UserMapper {
    /**
     * 获取用户信息进行数据库信息验证
     * allEntries=true方法调用后将立即清除缓存
     *
     * @param userPO 传入用户信息
     * @return 验证通过则返回用户信息，否则为null
     */
    UserPO getUser(UserPO userPO);

    /**
     * 验证用户名是否唯一
     *
     * @param name   传入用户名
     * @return  验证通过则返回用户名，否则为null
     */
    UserPO checkName(String name);

    /**
     * 插入用户信息
     *
     * @param userPO
     */
    void saveUser(UserPO userPO);

}
