package com.yijiupi.logindemo.controller;

import com.google.code.kaptcha.Constants;
import com.yijiupi.logindemo.pojo.UserVO;
import com.yijiupi.logindemo.service.UserService;
import com.yijiupi.logindemo.task.Producers;
import com.yijiupi.logindemo.util.ConstansUtil;
import com.yijiupi.logindemo.util.PageUtil;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;

/**
*@Author: ZhangZhe
*@Description  逻辑控制层 处理用户请求
*@Date: 2017/12/22
*/

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    @Autowired
    private UserService userService;

    @Autowired
    Producers producers;

    @RequestMapping("login")
    public ModelAndView userLogin(UserVO userVO, ModelAndView mv, String code, HttpServletRequest request) {
        LOGGER.info("=============进入userLogin()方法=============");
        HttpSession session = request.getSession();
        String codeException = (String)session.getAttribute(Constants.KAPTCHA_SESSION_KEY);
        userVO = userService.getUser(userVO);
        //查询当前执行用户
        Subject subject = SecurityUtils.getSubject();
        String name = userVO.getName();
        String password = userVO.getPassword();
        //获取用户授权登录令牌
        UsernamePasswordToken token = new UsernamePasswordToken(name, password);
        try{
            subject.login(token);
            //校对验证码
            if (codeException.equals(code)){
                mv.addObject("message", ConstansUtil.CONGRATULATION_LOGIN);
                mv.setViewName(PageUtil.SUCCESSPAGE);
                return mv;
            }
            //code不对 重新登录
            mv.addObject("message", ConstansUtil.CODE_ERROR);
            mv.setViewName(PageUtil.LOGINPAGE);
        }catch (AuthenticationException e){
            //token不对 重新登录
            token.clear();
            mv.addObject("message", ConstansUtil.USER_ERROR);
            mv.setViewName(PageUtil.LOGINPAGE);
        }
        return mv;
    }

    @RequestMapping("register")
    public ModelAndView register(UserVO userVO, ModelAndView mv){
        String name = userVO.getName();
        boolean check_result = userService.checkName(name);
        if (check_result){
            userService.insertUser(userVO);
            mv.addObject("message", ConstansUtil.CONGRATULATION_REGISTER);
            mv.setViewName(PageUtil.LOGINPAGE);
            return mv;
        }
        mv.addObject("message", ConstansUtil.USERNAME_EXISTED);
        mv.setViewName(PageUtil.REGISTER);
        return mv;
    }

    @RequestMapping(value = "/send", method = RequestMethod.GET)
    public void test(){
        UserVO userVO = new UserVO();
        userVO.setName("zhangzhe");
        userVO.setPassword("123456");
        producers.send(userVO);
    }
}
