package com.yijiupi.logindemo.service.impl;

import com.yijiupi.logindemo.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.core.io.FileSystemResource;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

/**
*@Author: ZhangZhe
*@Description  MainService实现类
*@Date: 2017/12/29
*/

@Service
public class MailServiceImpl implements MailService {

    @Autowired
    private JavaMailSender sender;

    @Value("${spring.mail.username}")
    private String from;

    private static final Logger LOGGER = LoggerFactory.getLogger(MailServiceImpl.class);

    /**
     * 发送基本文本邮件
     *
     * @param target        目的人
     * @param subject       标题
     * @param content       内容
     */
    @Override
    @Async
    public void sendSimpleMail(String target, String subject, String content) {
        SimpleMailMessage message = new SimpleMailMessage();
        message.setFrom(from);
        message.setTo(target);
        message.setSubject(subject);
        message.setText(content);
        try {
            sender.send(message);
            LOGGER.info("==========登录确认邮件已经发送。================");
        } catch (Exception e) {
            LOGGER.error("发送简单邮件时发生异常!", e);
        }
    }

    /**
     * 发送HTML格式邮件
     *
     * @param target        目的人
     * @param subject       标题
     * @param content       内容
     */
    @Override
    @Async
    public void sendHtmlMail(String target, String subject, String content) {
        MimeMessage message = sender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(target);
            helper.setSubject(subject);
            helper.setText(content, true);

            sender.send(message);
            LOGGER.info("=============html邮件已经发送。============");
        } catch (MessagingException e) {
            LOGGER.error("发送html邮件时发生异常！", e);
        }
    }

    /**
     * 发送带附件的邮件
     *
     * @param target        目的人
     * @param subject       标题
     * @param content       内容
     * @param filePath      文件路径
     */
    @Override
    @Async
    public void sendAttachmentsMail(String target, String subject, String content, String filePath) {
        MimeMessage message = sender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(target);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource file = new FileSystemResource(new File(filePath));
            String fileName = filePath.substring(filePath.lastIndexOf(File.separator));
            helper.addAttachment(fileName, file);

            sender.send(message);
            LOGGER.info("==========带附件的邮件已经发送。=========");
        } catch (MessagingException e) {
            LOGGER.error("发送带附件的邮件时发生异常！", e);
        }

    }

    /**
     * 发送含静态资源的邮件
     *
     * @param target        目的人
     * @param subject       标题
     * @param content       邮件内容，需要包括一个静态资源的id，比如：<img src=\"cid:rscId01\" >
     * @param rscPath       静态资源路径和文件名
     * @param rscId         静态资源id
     */
    @Override
    @Async
    public void sendInlineResourceMail(String target, String subject, String content, String rscPath, String rscId) {
        MimeMessage message = sender.createMimeMessage();

        try {
            //true表示需要创建一个multipart message
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(target);
            helper.setSubject(subject);
            helper.setText(content, true);

            FileSystemResource res = new FileSystemResource(new File(rscPath));
            helper.addInline(rscId, res);

            sender.send(message);
            LOGGER.info("==========嵌入静态资源的邮件已经发送。================");
        } catch (MessagingException e) {
            LOGGER.error("发送嵌入静态资源的邮件时发生异常！", e);
        }
    }

}
