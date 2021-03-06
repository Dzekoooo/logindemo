package com.yijiupi.logindemo.controller;

import com.yijiupi.logindemo.service.RedisService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

/**
*@Author: ZhangZhe
*@Description  请求页面控制
*@Date: 2017/12/23
*/

@Controller
public class BaseController {

    @Autowired
    private RedisService redisService;

    private static final Logger LOGGER = LoggerFactory.getLogger(BaseController.class);

    /**
     * 实现基本页面跳转
     *
     * @param page
     * @return
     */
    @RequestMapping("/to/{page}")
    public String page(@PathVariable("page") String page) {
        LOGGER.info("==============请求的页面是：= " + page);
        return page;
    }
}
