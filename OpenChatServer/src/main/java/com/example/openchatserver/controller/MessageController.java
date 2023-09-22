package com.example.openchatserver.controller;


import com.example.openchatserver.dto.SendMessageRequest;
import com.example.openchatserver.dto.SendMessageResponse;
import com.example.openchatserver.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;

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




}
