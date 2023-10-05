package com.example.openchatserver.service;


import com.example.openchatserver.dto.GetMessagesResponse;
import com.example.openchatserver.dto.SendMessageRequest;
import com.example.openchatserver.dto.SendMessageResponse;
import com.example.openchatserver.entity.Message;
import com.example.openchatserver.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private final MessageRepository messageRepository;
    private final KafkaProducer kafkaProducer;

    private final RedisTemplate<String, String> redisTemplate;

    @Transactional
    public SendMessageResponse sendMessage(SendMessageRequest request){

        Message message = new Message(request);
        messageRepository.save(message);
        kafkaProducer.sendMessageEvent(message);
        SendMessageResponse sendMessageResponse = new SendMessageResponse(message);
        return sendMessageResponse;

    }

    @Transactional
    public List<GetMessagesResponse> getMessages(){


        redisTemplate.opsForValue().increment("event_count",1);

        List<Message> messages =  messageRepository.findAll();

        List<GetMessagesResponse> messagesResponses = messages.stream()
                .map(GetMessagesResponse::new)
                .collect(Collectors.toList());

        return messagesResponses;

    }

    @Scheduled(fixedRate = 1000)
    public void checkHotChat() throws IOException {

        Long currentEventCount = Long.valueOf(redisTemplate.opsForValue().get("event_count"));
        System.out.println("redis count: "+currentEventCount + " hotchat : " + redisTemplate.opsForValue().get("hotchat"));

        if (currentEventCount >= 5000) {
            redisTemplate.opsForValue().set("hotchat", "true");
            System.out.println("hotchat created : "+currentEventCount);
        } else {
            // 700개 이하이면 hotchat 해제
            redisTemplate.delete("hotchat");
        }
        redisTemplate.opsForValue().set("event_count", "0");
    }








}
