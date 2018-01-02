package com.yijiupi.logindemo;

import com.yijiupi.logindemo.dao.UserMapper;
import com.yijiupi.logindemo.service.MailService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.ui.velocity.VelocityEngineUtils;

import java.util.HashMap;
import java.util.Map;

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

	@Autowired
	private MailService mailService;

//	@Autowired


//	@Autowired
//	private JavaMailSender javaMailSender;
//
//	@Value("${spring.mail.username}")
//	private String username;

	private String target = "479473984@qq.com";

	@Test
	public void sendSimpleMail(){
		mailService.sendSimpleMail(target, "主题：测试", "测试类邮件");
	}

//	@Test
//	public void sendHtmlMail(){
//		Map<String, Object> model = new HashMap<String, Object>();
//		model.put("message", "这是测试的内容。。。");
//		model.put("toUserName", "张三");
//		model.put("fromUserName", "老许");
//		String content = VelocityEngineUtils.mergeTemplateIntoString(velocityEngine, "welcome.vm", "UTF-8", model);
//
//		mailService.sendHtmlMail(target, "主题：html邮件", content);
//	}

	@Test
	public void sendAttachmentsMail() {
		mailService.sendAttachmentsMail(target, "主题：带附件的邮件", "有附件，请查收！", "C:\\Users\\admin\\Pictures\\Camera Roll\\timg.jpg");
	}

	@Test
	public void sendInlineResourceMail() {
		String rscId = "rscId001";
		mailService.sendInlineResourceMail(target,
				"主题：嵌入静态资源的邮件",
				"<html><body>这是有嵌入静态资源：<img src=\'cid:" + rscId + "\' ></body></html>",
				"C:\\Users\\admin\\Pictures\\Camera Roll\\timg.jpg",
				rscId);
	}



//	@Test
//	public void testSendSimple() {
//		SimpleMailMessage message = new SimpleMailMessage();
//		message.setFrom(username);
//		message.setTo("ksyo@vip.qq.com");
//		message.setSubject("你是智障吗");
//		message.setText("zzzzzz！");
//		javaMailSender.send(message);
//	}


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
