package com.yijiupi.logindemo.service;

import com.yijiupi.logindemo.pojo.UserVO;
import org.springframework.cache.annotation.Cacheable;

/**
*@Author: ZhangZhe
*@Description
*@Date: 2017/12/22
*/

public interface UserService {

    /**
     * 通过数据库反馈结果验证用户信息，成功返回UserVO对象，否则为null
     *
     * @param userVO
     * @return
     */
    UserVO getUser(UserVO userVO);

    /**
     * 验证用户名是否存在
     *
     * @param name
     * @return 验证通过返回true， 否则为false
     */
    boolean checkName(String name);

    /**
     * 添加用户信息
     *
     * @param userVO
     */
    void insertUser(UserVO userVO);
}
