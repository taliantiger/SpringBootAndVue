package org.talian.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.talian.model.Chat;
import org.talian.model.Message;

import java.security.Principal;

//@Controller
//public class GreetingController {
//    @MessageMapping("/hello")
//    @SendTo("/topic/greetings")
//    public Message greeting(Message message) throws Exception{
//        return message;
//    }
//}








/**
 * 消息点对点发送
 */
//@Controller
//public class GreetingController{
//    @Autowired
//    SimpMessagingTemplate messagingTemplate;
//
//    @MessageMapping("/hello")
//    public void greeting(Message message) throws Exception{
//        messagingTemplate.convertAndSend("/topic/greetings", message);
//    }
//}


@Controller
public class GreetingController{
    /**
     * 点对点消息使用SimpMessagingTemplate 来实现
     */
    @Autowired
    SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/hello")
    /***
     * 群发消息使用 @SendTo 注解来实现
     */
    @SendTo("/topic/greetings")
    public Message greeting(Message message) throws Exception{
        return message;
    }


    @MessageMapping("/chat")
    public void chat(Principal principal, Chat chat){
        String from = principal.getName();
        chat.setFrom(from);
        messagingTemplate.convertAndSendToUser(chat.getTo(), "/queue/chat", chat);
    }



}




























