package com.yijiupi.logindemo.controller;


import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
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

    private static final Logger LOGGR = LoggerFactory.getLogger(BaseController.class);

    @RequestMapping("/to/{page}")
    public String page(@PathVariable("page") String page) {
        LOGGR.info("==============请求的页面是：= " + page);
        return page;
    }
}
