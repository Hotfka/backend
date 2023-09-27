package com.example.publishserver.service;
import com.example.publishserver.controller.SSEController;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final SSEController sseController;

    @KafkaListener(topics = "sendMessage", groupId = "hotfka")
    public void consumeMessage(String messageUserName) throws IOException {
        System.out.println(String.format("Consumed message getUserName: %s", messageUserName));
        sseController.sendData(messageUserName);
    }

    @KafkaListener(topics = "sendReaction", groupId = "hotfka")
    public void consumeReaction(String messageUserName) throws IOException {
        System.out.println(String.format("Consumed message getUserName: %s", messageUserName));
        sseController.sendData(messageUserName);
    }

}