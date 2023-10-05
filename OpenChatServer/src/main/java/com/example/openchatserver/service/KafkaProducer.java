package com.example.openchatserver.service;

import com.example.openchatserver.entity.Message;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class KafkaProducer {
    private static final String TOPIC_MESSAGE = "sendMessage";
    private static final String TOPIC_REACTION = "sendReaction";
    private final KafkaTemplate<String, String> kafkaTemplate;
    public void sendMessageEvent(Message message) {
        System.out.println(String.format("Produce message getUserName : %s", message.getUserName()));
        this.kafkaTemplate.send(TOPIC_MESSAGE, message.getUserName());
    }

    public void sendReactionEvent(Message message) {
        System.out.println(String.format("Produce Reaction getUserName : %s", message.getUserName()));
        this.kafkaTemplate.send(TOPIC_REACTION, message.getUserName());
    }

}
