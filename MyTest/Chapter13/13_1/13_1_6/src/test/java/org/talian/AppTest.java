package org.talian;

//import freemarker.template.Configuration;
//import freemarker.template.Configuration;
//import freemarker.template.Template;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.talian.service.MailService;
import org.thymeleaf.TemplateEngine;
import org.thymeleaf.context.Context;

import java.io.File;
import java.io.StringWriter;


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


    @Test
    public void sendAttachFileMail(){
        mailService.sendAttachFileMail("1258367587@qq.com",
                "396318379@qq.com",
                "测试邮件主题",
                "测试邮件内容",
                new File("E:\\邮件附件.docx"));
    }



    @Test
    public void sendMailWithImg(){
        mailService.sendMailWithImg("1258367587@qq.com",
                "396318379@qq.com",
                "测试邮件主题(图片)",
                "<div>hello,这是一封带图片资源的邮件:" +
                "这是图片1:<div><img src='cid:p01'/></div>" +
                "这是图片2:<div><img src='cid:p02'/></div>" +
                "</div>",
                new String[]{"Y:\\DataSave\\图片\\test\\p1.png",
                "Y:\\DataSave\\图片\\test\\p2.png"},
                new String[]{"p01", "p02"});
    }



    //Freemarker
//    @Test
//    public void sendHtmlMail(){
//        try{
//            Configuration configuration = new Configuration(Configuration.VERSION_2_3_0);
//
////            ClassLoader loader = SendmailApplication.class.getClassLoader();
//            ClassLoader loader = App.class.getClassLoader();
//
//            configuration.setClassLoaderForTemplateLoading(loader,"ftl");
//            Template template = configuration.getTemplate("mailtemplate.ftl");
//            StringWriter mail = new StringWriter();
//            User user = new User();
//            user.setGender("男");
//            user.setUsername("塔里安菌");
//            template.process(user, mail);
//            mailService.sendHtmlMail("1258367587@qq.com",
//                    "396318379@qq.com",
//                    "测试邮件主题",
//                    mail.toString());
//        }catch(Exception e){
//            e.printStackTrace();
//        }
//    }


    //Thymeleaf
    @Autowired
    TemplateEngine templateEngine;
    @Test
    public void sendHtmlMailThymeleaf(){
        Context ctx = new Context();
        ctx.setVariable("username", "talian");
        ctx.setVariable("gender", "男");
        String mail = templateEngine.process("mailtemplate.html", ctx);
        mailService.sendHtmlMail("1258367587@qq.com",
                "396318379@qq.com",
                "测试邮件主题",
                mail);
    }

}

























