package com.example.publishserver.service;
import com.example.publishserver.controller.SSEController;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
@RequiredArgsConstructor
public class KafkaConsumer {

    private final SSEController sseController;
    private final RedisTemplate<String, String> redisTemplate;


    @KafkaListener(topics = "sendMessage", groupId = "hotfka")
    public void consumeMessage(String messageUserName) throws IOException {
        System.out.println(String.format("Consumed message getUserName: %s", messageUserName));
        sseController.sendMessageEvent(messageUserName);
    }

    @KafkaListener(topics = "sendReaction", groupId = "hotfka")
    public void consumeReaction(String messageUserName) throws IOException {
        System.out.println(String.format("Consumed reaction getUserName: %s", messageUserName));
        sseController.sendReactionEvent(messageUserName);
    }


}