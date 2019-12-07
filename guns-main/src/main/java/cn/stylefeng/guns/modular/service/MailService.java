package cn.stylefeng.guns.modular.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.stereotype.Service;

@Service
public class MailService {

    @Autowired
    public JavaMailSenderImpl mailSender;

    public void sendMail(String mail){
        SimpleMailMessage simpleMailMessage = new SimpleMailMessage();

        simpleMailMessage.setSubject("用户正在审核中标题");
        simpleMailMessage.setText("您好，您申请的账号正在审核中，请您耐心等待！");
        simpleMailMessage.setTo(mail); //申请账号的用户邮箱地址
        simpleMailMessage.setFrom("third_e@163.com"); // 服务器发送邮箱地址

        mailSender.send(simpleMailMessage);
    }
}
