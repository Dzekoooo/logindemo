package com.yijiupi.logindemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;

/**
*@Author: ZhangZhe
*@Description 入口
*@Date: 2017/12/22
*/
@SpringBootApplication
@MapperScan(value = "com.yijiupi.logindemo.dao")
public class LoginDemoApplication extends SpringBootServletInitializer implements CommandLineRunner{

	public static void main(String[] args) {
		SpringApplication.run(LoginDemoApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

	}

	@Override
	protected SpringApplicationBuilder configure(SpringApplicationBuilder builder) {
		return builder.sources(LoginDemoApplication.class);
	}


}
