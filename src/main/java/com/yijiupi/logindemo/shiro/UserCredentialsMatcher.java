package com.yijiupi.logindemo.shiro;

import org.apache.shiro.authc.AuthenticationInfo;
import org.apache.shiro.authc.AuthenticationToken;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.authc.credential.SimpleCredentialsMatcher;

/**
*@Author: ZhangZhe
*@Description 用户的 证书匹配器，校验用户的证书（密码）是否匹配
*@Date: 2017/12/25
*/

public class UserCredentialsMatcher extends SimpleCredentialsMatcher {

    /**
     * 从 token 中取出 password 进行加密，返回到shiro manager中心校验
     * @param token user token
     * @param info user 认证信息
     * @return 证书匹配器 到shiro安全管理中心
     */
    @Override
    public boolean doCredentialsMatch(AuthenticationToken token, AuthenticationInfo info) {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        String username = usernamePasswordToken.getUsername();
        String password = String.valueOf(usernamePasswordToken.getPassword());
        /*
        * 取用户的明文密码password，使用用户的 name 作为盐值 salt
        * 生成加密后的密文传入shiro manger 中心校验
        * */
        String md5Password = Md5EnCoder.getMD5Hash(password, username);
        usernamePasswordToken.setPassword(md5Password.toCharArray());
        return super.doCredentialsMatch(usernamePasswordToken, info);
    }
}
