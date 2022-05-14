package org.talian.config;

import org.springframework.amqp.core.Binding;
import org.springframework.amqp.core.BindingBuilder;
import org.springframework.amqp.core.DirectExchange;
import org.springframework.amqp.core.Queue;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class RabbitDirectConfig {
    public final static String DIRECTNAME = "talian-direct";

    @Bean
    Queue queue(){
        return new Queue("hello-queue");
    }

    @Bean
    DirectExchange directExchange(){
        return new DirectExchange(DIRECTNAME, true, false);
    }

    @Bean
    Binding binding(){
        return BindingBuilder.bind(queue()).to(directExchange()).with("direct");
    }
}
