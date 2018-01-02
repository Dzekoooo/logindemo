package com.yijiupi.logindemo;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.builder.SpringApplicationBuilder;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.scheduling.annotation.EnableAsync;

import java.net.InetAddress;
import java.net.UnknownHostException;

/**
*@Author: ZhangZhe
*@Description 入口
*@Date: 2017/12/22
*/

@EnableAsync
@SpringBootApplication
@MapperScan(value = "com.yijiupi.logindemo.dao")
public class LoginDemoApplication extends SpringBootServletInitializer implements CommandLineRunner{

	public static void main(String[] args) throws UnknownHostException {
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
