package com.example.openchatserver.service;


import com.example.openchatserver.dto.SendMessageResponse;
import com.example.openchatserver.entity.Message;
import com.example.openchatserver.repository.MessageRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class MessageService {

    private MessageRepository messageRepository;

    @Transactional
    public SendMessageResponse sendMessage(sendMessageRequest request){

        Message message = new Message(request);
        messageRepository.save(message);

        SendMessageResponse sendMessageResponse = new SendMessageResponse(message);

        return sendMessageResponse;

    }






}
