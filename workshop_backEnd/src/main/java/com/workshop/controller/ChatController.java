package com.workshop.controller;

import com.workshop.config.WebSocket.model.ChatMessage;
import org.springframework.messaging.handler.annotation.MessageMapping;
import org.springframework.messaging.handler.annotation.Payload;
import org.springframework.messaging.handler.annotation.SendTo;
import org.springframework.stereotype.Controller;


import java.sql.Date;

@Controller
public class ChatController {
    @MessageMapping("/chat")
    @SendTo("topic/messages")
    public ChatMessage sendMessage(@Payload ChatMessage chatMessage){
        chatMessage.setTimestamp((new Date(12)));
        return  chatMessage;
    }
}
