package org.sang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.web.servlet.ServletComponentScan;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
//@ComponentScan
@ServletComponentScan
public class App {
    public static void main(String[] args){
        SpringApplication.run(App.class, args);
    }
}
