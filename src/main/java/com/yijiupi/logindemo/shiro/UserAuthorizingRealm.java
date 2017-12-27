package com.yijiupi.logindemo.shiro;


import com.yijiupi.logindemo.pojo.UserVO;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.*;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
*@Author: ZhangZhe
*@Description 用户授权范围管理
*@Date: 2017/12/23
*/
public class UserAuthorizingRealm extends AuthorizingRealm {

    public static final Logger LOGGER = LoggerFactory.getLogger(UserAuthorizingRealm.class);

    /**
     * 用户授权信息管理
     *
     * @param principals 授权所依据的重要信息
     * @return AuthorizationInfo 授权信息
     */
    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        return null;
    }

    /**
     * 登录验证
     *
     * @param token 用户认证令牌
     * @return AuthenticationInfo 认证信息
     * @throws AuthenticationException
     */
    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) token;
        List<UserVO> list = new ArrayList<UserVO>();
        Map<String, String> map = new HashMap<String, String>(5);
        UserVO user1 = new UserVO();
        //测试的特权用户list
        user1.setName("wifi");
        user1.setPassword("367f61214b5a0016fdf82d511655b6c1");
        UserVO testUser = new UserVO();
        testUser.setName("test");
        testUser.setPassword("6618bc8e340dfdf7ff4c1c11b7be061d");
        list.add(testUser);
        list.add(user1);
        AuthenticationInfo info = new SimpleAuthenticationInfo(list.get(0), list.get(0).getPassword(), this.getName());
        return info;
    }
}
