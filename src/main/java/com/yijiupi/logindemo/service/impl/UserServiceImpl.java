package com.yijiupi.logindemo.service.impl;

import com.yijiupi.logindemo.dao.UserMapper;
import com.yijiupi.logindemo.pojo.UserPO;
import com.yijiupi.logindemo.pojo.UserVO;
import com.yijiupi.logindemo.service.UserService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
*@Author: ZhangZhe
*@Description UserSerVice的实现类
*@Date: 2017/12/22
*/

@Service
public class UserServiceImpl implements UserService {

    private final Logger LOGGER = LoggerFactory.getLogger(UserServiceImpl.class);

    @Autowired
    private UserMapper userMapper;

    /**
     * 登录
     *
     * @param userVO
     * @return
     */
    @Override
    public UserVO getUser(UserVO userVO) {
        LOGGER.info("=============进入getUser方法验证登录=============");
        UserPO userPO = new UserPO();
        UserVO mapperUserVO = new UserVO();
        //将VO转成PO
        BeanUtils.copyProperties(userVO, userPO);
        LOGGER.info("===========进入userMapper的getUser()方法");
        userPO = userMapper.getUser(userPO);
        if (null != userPO){
            BeanUtils.copyProperties(userPO, mapperUserVO);
        }
        return mapperUserVO;
    }

    /**
     * 检验
     *
     * @param name
     * @return
     */
    @Override
    public boolean checkName(String name) {
        LOGGER.info("==========进入checkName方法验证用户是否存在=========");
        LOGGER.info("==========进入 userMapper的checkName()方法========");
        UserPO userPO = userMapper.checkName(name);
        boolean result = false;
        if (null == userPO) {
            result = true;
        }
        return result;
    }

    /**
     * 添加
     *
     * @param userVO
     */
    @Override
    public void insertUser(UserVO userVO) {
        LOGGER.info("==========进入insertUser方法添加用户信息============");
        UserPO userPO = new UserPO();
        //将VO转成PO
        BeanUtils.copyProperties(userVO, userPO);
        LOGGER.info("===========进入dao下的saveUser方法===================");
        userMapper.saveUser(userPO);
    }
}
