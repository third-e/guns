package cn.stylefeng.guns.modular.service;

import cn.stylefeng.guns.base.consts.ConstantsContext;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.javamail.JavaMailSenderImpl;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import sun.security.pkcs11.wrapper.Constants;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
public class MailService {

    /**
     * 发送邮件提示用户注册情况
     */
    @Autowired
    public JavaMailSenderImpl mailSender;

    public void sendMail(String mail){
        MimeMessage mimeMessage = mailSender.createMimeMessage();
        MimeMessageHelper helper = null;
        try {
            helper = new MimeMessageHelper(mimeMessage,true);
            helper.setSubject("用户初次注册发送邮件信息");
            if(ConstantsContext.getRegisteredReviewOpen()){
                helper.setText("您好，您申请的账号审核通过，现可以登录了！",true);
            }else {
                helper.setText("您好，您申请的账号正在审核中，请您耐心等待！",true);
            }
            helper.setTo(mail); //申请账号的用户邮箱地址
            helper.setFrom("third_e@163.com"); // 服务器发送邮箱地址
            mailSender.send(mimeMessage);
        } catch (MessagingException e) {
            e.printStackTrace();
        }



    }
}
