package com.yijiupi.logindemo.config.rabbit;

import org.springframework.amqp.rabbit.connection.ConnectionFactory;
import org.springframework.amqp.rabbit.core.RabbitAdmin;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

/**
*@Author: ZhangZhe
*@Description RabbitMQConfig
*@Date: 2017/12/27
*/

@Configuration
public class RabbitMQConfig {
    /**
     * 注入配置文件属性
     */
    @Value("${spring.rabbitmq.addresses}")
    String addresses;
    @Value("${spring.rabbitmq.username}")
    String username;
    @Value("${spring.rabbitmq.password}")
    String password;
    @Value("${spring.rabbitmq.virtual-host}")
    String vHost;

    /**
     * 创建 ConnectionFactory
     *
     * @return
     * @throws Exception
     */
    @Bean
    public ConnectionFactory connectionFactory() throws Exception {
        return RabbitUtil.connectionFactory(addresses, username, password, vHost);
    }

    /**
     * 创建 RabbitAdmin
     *
     * @param connectionFactory
     * @return
     * @throws Exception
     */
    @Bean
    public RabbitAdmin rabbitAdmin(ConnectionFactory connectionFactory) throws Exception {
        RabbitAdmin rabbitAdmin = new RabbitAdmin(connectionFactory);
        return rabbitAdmin;
    }

}
