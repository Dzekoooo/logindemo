package com.yijiupi.logindemo.config;

import com.yijiupi.logindemo.shiro.UserAuthorizingRealm;
import com.yijiupi.logindemo.shiro.UserCredentialsMatcher;
import org.apache.shiro.authc.credential.CredentialsMatcher;
import org.apache.shiro.mgt.SecurityManager;
import org.apache.shiro.spring.web.ShiroFilterFactoryBean;
import org.apache.shiro.web.mgt.DefaultWebSecurityManager;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.LinkedHashMap;
import java.util.Map;

/**
*@Author: ZhangZhe
*@Description shiro配置
*@Date: 2017/12/23
*/


@Configuration
public class ShiroConfig {
    /**
     * 定义shiro 过滤器
     *
     * @return
     */
    @Bean
    public ShiroFilterFactoryBean ShiroFilter() {
        ShiroFilterFactoryBean shiroFilterFactoryBean = new ShiroFilterFactoryBean();
        shiroFilterFactoryBean.setSecurityManager(securityManager());
        // 定义拦截规则
        Map<String, String> filters = new LinkedHashMap<>();
        /**
         * 配置不会被拦截的链接
         * anon url匿名访问
         * authc  url必须认证ok才可以通过
         */
        filters.put("/user/**", "anon");
        filters.put("/user/login", "authc");
        //前往登录页面
        shiroFilterFactoryBean.setLoginUrl("/user/login");
        shiroFilterFactoryBean.setFilterChainDefinitionMap(filters);
        return shiroFilterFactoryBean;
    }

    /**
     * 定义shiro 安全中心
     *
     * @return
     */
    @Bean
    public SecurityManager securityManager() {
        DefaultWebSecurityManager securityManager = new DefaultWebSecurityManager();
        securityManager.setRealm(userAuthorizingRealm());
        return securityManager;
    }


    /**
     * 定义 user 认证范围
     *
     * @return
     */
    @Bean
    public UserAuthorizingRealm userAuthorizingRealm() {
        UserAuthorizingRealm userAuthorizingRealm = new UserAuthorizingRealm();
        // 设置user  证书匹配器
        userAuthorizingRealm.setCredentialsMatcher(userCredentialsMatcher());
        return userAuthorizingRealm;
    }


    /**
     * 定义 user 证书匹配器
     *
     * @return
     */
    @Bean
    public CredentialsMatcher userCredentialsMatcher() {
        return new UserCredentialsMatcher();
    }



}
