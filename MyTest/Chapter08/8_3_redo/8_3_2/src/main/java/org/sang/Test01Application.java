package org.sang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan
public class Test01Application {
    public static void main(String[] args) {
        SpringApplication.run(Test01Application.class, args);
    }
}
