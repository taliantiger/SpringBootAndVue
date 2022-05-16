package org.talian.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;
import java.io.File;

@Component
public class MailService {
    @Autowired
    JavaMailSender javaMailSender;

    public void sendSimpleMail(String from, String to, String cc,
                               String subject, String content){
        SimpleMailMessage simMsg = new SimpleMailMessage();
        simMsg.setFrom(from);
        simMsg.setTo(to);
        simMsg.setCc(cc);
        simMsg.setSubject(subject);
        simMsg.setText(content);
        javaMailSender.send(simMsg);
    }


    // 发送带附件的邮件
    public void sendAttachFileMail(String from, String to,
                                   String subject, String content, File file){
        try{
            MimeMessage message = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(message, true);
            helper.setFrom(from);
            helper.setTo(to);
            helper.setSubject(subject);
            helper.setText(content);
            helper.addAttachment(file.getName(), file);
            javaMailSender.send(message);
        }catch(MessagingException e){
            e.printStackTrace();
        }
    }
}
