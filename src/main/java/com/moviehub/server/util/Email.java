package com.moviehub.server.util;

import com.sun.mail.util.MailSSLSocketFactory;

import javax.mail.*;
import java.util.Properties;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

public class Email {
    public static boolean sendEmail(String content,
                                 String[] toEmilAddress) throws Exception {
        boolean flag = false;
        Properties props = new Properties();
        String title = "COME FROM MOVIEHUB";
        String sendEmailPwd = "WUVRXXASJCQQBOGW";
        String sendEmail = "zeeeeeeason@163.com";
        // 开启debug调试，以便在控制台查看
        props.setProperty("mail.debug", "true");
        // 设置邮件服务器主机名
        props.setProperty("mail.host", "smtp.163.com");
        // 发送服务器需要身份验证
        props.setProperty("mail.smtp.auth", "true");
        // 发送邮件协议名称
        props.setProperty("mail.transport.protocol", "smtp");

        // 开启SSL加密，否则会失败
        MailSSLSocketFactory sf = new MailSSLSocketFactory();
        sf.setTrustAllHosts(true);
        props.put("mail.smtp.ssl.enable", "true");
        props.put("mail.smtp.ssl.socketFactory", sf);

        Session session = Session.getInstance(props);
        Message msg = new MimeMessage(session);
        // 发送的邮箱地址
        msg.setFrom(new InternetAddress(sendEmail));
        // 设置标题
        msg.setSubject(title);
        // 设置内容
        msg.setContent(content, "text/html;charset=gbk;");
        Transport transport = session.getTransport();
        // 设置服务器以及账号和密码
        // 这里端口改成465
        transport.connect("smtp.163.com", sendEmail, sendEmailPwd);
        // 发送到的邮箱地址
        transport.sendMessage(msg, getAddress(toEmilAddress));


        if(transport!=null){

            try {
                transport.close();
                return true;
            } catch (MessagingException e) {
                e.printStackTrace();
                return true;
            }
        }
        return false;
    }public static boolean isEmail(String email) {
        if (email == null || "".equals(email)) {// 数据为空
            return false;
        }
        String regex = "\\w+@\\w+\\.\\w+";
        return email.matches("[\\w\\.\\-]+@([\\w\\-]+\\.)+[\\w\\-]+");
    }

    /**
     *

     * @Title: getAddress

     * @Description: 遍历收件人信息

     * @param emilAddress
     * @return
     * @throws Exception

     * @return: Address[]
     */
    private static Address[] getAddress(String[] emilAddress) throws Exception {
        Address[] address = new Address[emilAddress.length];
        for (int i = 0; i < address.length; i++) {
            address[i] = new InternetAddress(emilAddress[i]);
        }
        return address;
    }

    public static void main(String[] args) {

    }

}
