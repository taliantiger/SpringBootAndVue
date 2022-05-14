package org.talian;

import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;

/**
 * 配置一个消费者
 */
@Component
public class DirectReceiver {
    @RabbitListener(queues = "hello-queue")
    public void handler(String msg){
        System.out.println("DirectReceiver:" + msg);
    }
}
