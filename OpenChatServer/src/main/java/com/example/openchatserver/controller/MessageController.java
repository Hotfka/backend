package com.example.openchatserver.controller;


import com.example.openchatserver.dto.GetMessagesResponse;
import com.example.openchatserver.dto.SendMessageRequest;
import com.example.openchatserver.dto.SendMessageResponse;
import com.example.openchatserver.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/api/v1/sendMessage")
    public ResponseEntity<SendMessageResponse> sendMessage(@RequestBody SendMessageRequest request){

        SendMessageResponse savedMessage =  messageService.sendMessage(request);

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedMessage);
    }


    @GetMapping("/api/v1/getMessages")
    public ResponseEntity<List<GetMessagesResponse>> getMessages(){

        List<GetMessagesResponse> messagesResponse = messageService.getMessages();

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(messagesResponse);
    }




}
