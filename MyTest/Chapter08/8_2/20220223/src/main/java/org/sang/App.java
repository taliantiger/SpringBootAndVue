package org.sang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class App {
    public static void main(String[] args) {

//        添加了spring-boot-devtools依赖，但是不想使用自动重启特性
        System.setProperty("spring.devtools.restart.enabled", "false");

        SpringApplication.run(App.class, args);
    }
}
