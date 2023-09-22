package com.example.openchatserver.controller;


import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Controller
@RequiredArgsConstructor
public class MessageController {

    private final MessageService messageService;

    @PostMapping("/api/v1/sendMessage")
    public ResponseEntity<SendMessageResponse> sendMessage(@RequestBody sendMessageRequest request){

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(savedCandy);
    }




}
