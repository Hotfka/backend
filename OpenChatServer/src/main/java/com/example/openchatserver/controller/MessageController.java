package com.example.openchatserver.controller;


import com.example.openchatserver.dto.GetMessagesResponse;
import com.example.openchatserver.dto.SendMessageRequest;
import com.example.openchatserver.dto.SendMessageResponse;
import com.example.openchatserver.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;
    private final RedisTemplate<String, String> redisTemplate;


    @PostMapping("/api/v1/sendMessage")
    public ResponseEntity<SendMessageResponse> sendMessage(@RequestBody SendMessageRequest request){

        SendMessageResponse savedMessage =  messageService.sendMessage(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedMessage);
    }






    @GetMapping("/api/v1/getMessages")
    public ResponseEntity<?> getMessages(){

        List<GetMessagesResponse> messagesResponse = messageService.getMessages();

        String hotchat = redisTemplate.opsForValue().get("hotchat");

        if ("true".equals(hotchat)) {
            // hotchat 상황이면 스로틀링을 적용
            return ResponseEntity.status(HttpStatus.TOO_MANY_REQUESTS).body("Too many requests. Try again later.");
        }

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(messagesResponse);
    }




}
