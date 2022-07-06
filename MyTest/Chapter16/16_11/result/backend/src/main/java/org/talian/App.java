package org.talian;

import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.cache.annotation.EnableCaching;
import org.springframework.context.annotation.ComponentScan;

@EnableAutoConfiguration
@ComponentScan

@MapperScan("org.talian.mapper")

/**
 * 当你在配置类(@Configuration)上使用@EnableCaching注解时，
 * 会触发一个post processor，这会扫描每一个spring bean，
 * 查看是否已经存在注解对应的缓存。如果找到了，
 * 就会自动创建一个代理拦截方法调用，使用缓存的bean执行处理
 */
@EnableCaching
public class App {
    public static void main(String[] args) {
        SpringApplication.run(App.class, args);
    }
}
