package org.talian.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.format.FormatterRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;
import org.talian.common.DateConverter;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


/**
 * 通过Mvc，添加字符串转Date格式的方法
 */
@Configuration
public class WebMvcConfig implements WebMvcConfigurer {
    @Override
    public void addFormatters(FormatterRegistry registry){
        registry.addConverter(new DateConverter());
    }

    @Bean
    public ExecutorService executorService(){
        return Executors.newCachedThreadPool();
    }

}
