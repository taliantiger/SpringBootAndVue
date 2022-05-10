package org.talian;

import org.apache.activemq.command.ActiveMQQueue;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;

import javax.jms.Queue;

@ComponentScan
@EnableAutoConfiguration
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class);
    }

// 由ActiveMQ提供一个消息队列Bean实例
    @Bean
    Queue queue(){
        return new ActiveMQQueue("amq");
    }
}



