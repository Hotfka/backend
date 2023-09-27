package com.example.openchatserver.service;


import com.example.openchatserver.dto.SendReactionRequest;
import com.example.openchatserver.dto.SendReactionResponse;
import com.example.openchatserver.entity.Message;
import com.example.openchatserver.entity.Reaction;
import com.example.openchatserver.repository.MessageRepository;
import com.example.openchatserver.repository.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.apache.kafka.common.network.Send;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
@RequiredArgsConstructor
public class ReactionService {

    private final ReactionRepository reactionRepository;
    private final MessageRepository messageRepository;

    private final KafkaProducer kafkaProducer;


    @Transactional
    public SendReactionResponse sendReaction(SendReactionRequest request){

        Message message =  messageRepository.findById(request.getMessageId()).orElseThrow(IllegalArgumentException::new);
        Reaction reaction = new Reaction(request);

        reaction.setMessage(message);
        message.getReactions().add(reaction);

        reactionRepository.save(reaction);
        messageRepository.save(message);


        kafkaProducer.sendReactionEvent(message);


        SendReactionResponse sendReactionResponse = new SendReactionResponse(request,reaction.getId());
        return sendReactionResponse;
    }

}
