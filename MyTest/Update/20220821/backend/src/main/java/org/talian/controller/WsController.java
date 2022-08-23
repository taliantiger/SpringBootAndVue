package org.talian.controller;



import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.messaging.simp.SimpMessagingTemplate;
import org.springframework.stereotype.Controller;
import org.talian.bean.ChatResp;

import java.security.Principal;


/**
 * 创建消息转发Controller
 */

@Controller
public class WsController {
    @Autowired
//    调用的包的名称不能写错，有些是缩写，有些是全写，写错了一样无法找到对应的包。
//    SimpleMessagingTemplate messagingTemplate;
    SimpMessagingTemplate messagingTemplate;


    @MessageMapping("/ws/chat")
    public void handleChat(Principal principal, String msg){
        String destUser = msg.substring(msg.lastIndexOf(";") + 1, msg.length());
        String message = msg.substring(0, msg.lastIndexOf(";"));
        messagingTemplate.convertAndSendToUser(destUser,
                "/queue/chat",
                        new ChatResp(message, principal.getName()) );
    }

    @MessageMapping("/ws/nf")
    @SendTo("/topic/nf")
    public String handleNF(){
        return "系统消息";
    }

}
