package com.yijiupi.logindemo.controller;

import java.awt.image.BufferedImage;
import javax.imageio.ImageIO;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.yijiupi.logindemo.service.RedisService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import com.google.code.kaptcha.Constants;
import com.google.code.kaptcha.Producer;

/**
*@Author: ZhangZhe
*@Description  kaptcha
*@Date: 2017/12/26
*/

@Controller
public class CaptchaController {

    @Autowired
    private Producer captchaProducer;

    @Autowired
    private RedisService redisService;

    /**
     * 获取验证码并编码成图片输出
     *
     * @param request
     * @param response
     * @return
     * @throws Exception
     */
    @RequestMapping("kaptcha.jpg")
    public ModelAndView getKaptchaImage(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession session = request.getSession();
        //禁止页面缓存
        response.setDateHeader("Expires", 0);
        response.setHeader("Cache-Control", "no-store, no-cache, must-revalidate");
        response.addHeader("Cache-Control", "post-check=0, pre-check=0");
        response.setHeader("Pragma", "no-cache");
        response.setContentType("image/jpeg");
        String capText = captchaProducer.createText();
        session.setAttribute(Constants.KAPTCHA_SESSION_KEY, capText);
        redisService.set("code", capText);
        BufferedImage bi = captchaProducer.createImage(capText);
        ServletOutputStream out = response.getOutputStream();
        //输出验证图片到页面
        ImageIO.write(bi, "jpg", out);
        try {
            out.flush();
        } finally {
            out.close();
        }
        String code = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        System.out.println("===============验证码是: " + code + "=============");
        return null;
    }

}
