package org.sang.cache;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan

//添加@EnableCaching注解后开启缓存
@EnableCaching
public class CacheApplication {
    public static void main(String[] args){
        SpringApplication.run(CacheApplication.class, args);
    }

}
