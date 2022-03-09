package org.sang;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan

@EnableCaching
public class RediscacheApplication {
    public static void main(String[] args) {
        SpringApplication.run(RediscacheApplication.class, args);
    }
}
