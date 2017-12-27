package com.yijiupi.logindemo.task;

import com.yijiupi.logindemo.pojo.UserVO;
import org.springframework.amqp.rabbit.core.RabbitMessagingTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

/**
*@Author: ZhangZhe
*@Description Producers
*@Date: 2017/12/27
*/

@Service
public class Producers {

    @Qualifier("msgMessageTemplate")
    @Autowired
    RabbitMessagingTemplate rabbitSendTemplate;

    public void send(UserVO userVO) {
        System.out.println("send start.....");
        rabbitSendTemplate.convertAndSend("default.topic", "test2.send", userVO);
    }
}
