package com.yijiupi.logindemo.service;

/**
*@Author: ZhangZhe
*@Description
*@Date: 2017/12/29
*/

public interface MailService {

    /**
     * 发送文本邮件
     *
     * @param target        目的人
     * @param subject       标题
     * @param content       内容
     */
    void sendSimpleMail(String target, String subject, String content);

    /**
     * 发送html格式邮件
     *
     * @param target        目的人
     * @param subject       标题
     * @param content       内容
     */
    void sendHtmlMail(String target, String subject, String content);

    /**
     * 发送带附件的邮件
     *
     * @param target        目的人
     * @param subject       标题
     * @param content       内容
     * @param filePath      文件路径
     */
    void sendAttachmentsMail(String target, String subject, String content, String filePath);

    /**
     * 发送嵌入静态资源（一般是图片）的邮件
     *
     * @param target        目的人
     * @param subject       标题
     * @param content       邮件内容，需要包括一个静态资源的id，比如：<img src=\"cid:rscId01\" >
     * @param rscPath       静态资源路径和文件名
     * @param rscId         静态资源id
     */
    void sendInlineResourceMail(String target, String subject, String content, String rscPath, String rscId);

}
