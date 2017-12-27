package com.yijiupi.logindemo;

import com.yijiupi.logindemo.dao.UserMapper;
import com.yijiupi.logindemo.pojo.UserPO;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
*@Author: ZhangZhe
*@Description 测试与数据库的连接
*@Date: 2017/12/23
*/
@RunWith(SpringRunner.class)
@SpringBootTest
public class LoginDemoApplicationTests {

	@Autowired
	private UserMapper userMapper;


	/**
	 * 测试注册
	 */
//	@Test
//	public void registerTest() {
//		UserPO user = new UserPO();
//		user.setName("eeee");
//		user.setPassword("123456");
//		userMapper.saveUser(user);
//	}

	/**
	 * 测试登录
	 */
//	@Test
//	public void loginTest(){
//		UserPO userPO = new UserPO();
//		userPO.setName("test");
//		userPO.setPassword("test");
//		userPO = userMapper.getUser(userPO);
//		if (null == userPO){
//			System.out.println("getUser失败");
//		}
//		System.out.println("getUser成功");
//	}

	/**
	 * 测试匹配
	 */
//	@Test
//	public void checkTest(){
//		UserPO userPO = userMapper.checkName("test");
//		if (null == userPO){
//			System.out.println("不存在");
//		} else {
//			System.out.println("存在");
//		}
//	}

}
