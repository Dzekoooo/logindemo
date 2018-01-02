package com.yijiupi.logindemo.util;

import org.springframework.web.servlet.ModelAndView;

/**
*@Author: ZhangZhe
*@Description 封装ModleAndView
*@Date: 2017/12/28
*/

public class CreateView {
    public static ModelAndView createView(String msg, String viewName) {
        ModelAndView mv = new ModelAndView();
        mv.addObject("message", msg);
        mv.setViewName(viewName);
        return mv;
    }
}
