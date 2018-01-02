package com.yijiupi.logindemo.controller;

import com.yijiupi.logindemo.pojo.UserVO;
import com.yijiupi.logindemo.service.MailService;
import com.yijiupi.logindemo.service.RedisService;
import com.yijiupi.logindemo.service.UserService;
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
import org.springframework.web.servlet.ModelAndView;
import static com.yijiupi.logindemo.util.CreateView.createView;

/**
*@Author: ZhangZhe
*@Description  逻辑控制层 处理用户请求
*@Date: 2017/12/22
*/

@Controller
@RequestMapping("/user")
public class UserController {

    private static final Logger LOGGER = LoggerFactory.getLogger(UserController.class);

    private String target = "479473984@qq.com";

    @Autowired
    private UserService userService;

    @Autowired
    private RedisService redisService;

    @Autowired
    private MailService mailService;

    /**
     * 登录逻辑
     *
     * @param userVO
     * @param mv
     * @param code
     * @return      登录页面、成功页面
     */
    @RequestMapping("login")
    public ModelAndView userLogin(UserVO userVO, ModelAndView mv, String code) {
        LOGGER.info("=============进入userLogin()方法=============");
        //从redis获取code信息
        String codeException = redisService.get("code");
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
                mailService.sendSimpleMail(target, "主题：登录提醒", "您的账号在ip为" +  redisService.get("requesIP") + "登录");
                mv = createView(ConstansUtil.CONGRATULATION_LOGIN, PageUtil.SUCCESSPAGE);
                return mv;
            }
            //code不对 重新登录
            mv = createView(ConstansUtil.CODE_ERROR, PageUtil.LOGINPAGE);
        }catch (AuthenticationException e){
            //token不对 重新登录
            token.clear();
            mv = createView(ConstansUtil.USER_ERROR, PageUtil.LOGINPAGE);
        }
        return mv;
    }

    /**
     * 注册逻辑
     *
     * @param userVO
     * @param mv
     * @return     登录页面、注册页面
     */
    @RequestMapping("register")
    public ModelAndView register(UserVO userVO, ModelAndView mv){
        String name = userVO.getName();
        boolean check_result = userService.checkName(name);
        if (check_result){
            userService.insertUser(userVO);
            mv = createView(ConstansUtil.CONGRATULATION_REGISTER, PageUtil.LOGINPAGE);
            return mv;
        }
        mv = createView(ConstansUtil.USERNAME_EXISTED, PageUtil.REGISTERPAGE);
        return mv;
    }
}
