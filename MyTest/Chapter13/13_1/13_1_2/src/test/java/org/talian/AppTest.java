package org.talian;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.talian.service.MailService;


@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class AppTest {
    @Autowired
    MailService mailService;

    @Test
    public void sendSimpleMail(){
        mailService.sendSimpleMail("1258367587@qq.com",
                "396318379@qq.com",
                "taliantiger@gmail.com",
                "测试邮件主题",
                "测试邮件内容");
    }
}