package com.example.publishserver.service;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Service;

import java.io.IOException;

@Service
public class KafkaConsumer {

    @KafkaListener(topics = "sendMessage", groupId = "hotfka")
    public void consume(String message) throws IOException {
        System.out.println(String.format("Consumed message : %s", message));
    }

}