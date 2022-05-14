package org.talian;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.core.MessageBuilder;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;
import org.talian.config.RabbitFanoutConfig;
import org.talian.config.RabbitHeaderConfig;
import org.talian.config.RabbitTopicConfig;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = App.class)
public class AppTest {
    @Autowired
    RabbitTemplate rabbitTemplate;

    @Test
    public void directTest(){
        rabbitTemplate.convertAndSend("hello-queue", "hello direct!");
    }





    @Test
    public void fanoutTest(){
        rabbitTemplate.convertAndSend(RabbitFanoutConfig.FANOUTNAME,
                null,
                "hello fanout!");
    }




    @Test
    public void topicTest(){
        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,
                "xiaomi.news", "小米新闻...");

        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,
                "huawei.news", "华为新闻...");

        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,
        "xiaomi.phone", "小米手机");

        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,
                "huawei.phone", "华为手机");

        rabbitTemplate.convertAndSend(RabbitTopicConfig.TOPICNAME,
                "phone.news", "手机新闻...");
    }




    @Test
    public void headerTest(){
        Message nameMsg = MessageBuilder
                .withBody("hello header! name-queue____".getBytes())
                .setHeader("name","talian").build();

        Message ageMsg = MessageBuilder
                .withBody("hello header! age-queue____".getBytes())
                .setHeader("age", "99").build();

        rabbitTemplate.send(RabbitHeaderConfig.HEADNAME, null, ageMsg);
        rabbitTemplate.send(RabbitHeaderConfig.HEADNAME, null, nameMsg);
    }
}

