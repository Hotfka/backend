package com.example.openchatserver.service;


import com.example.openchatserver.dto.SendReactionResponse;
import com.example.openchatserver.repository.ReactionRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
@RequiredArgsConstructor
public class ReactionService {

    private final ReactionRepository reactionRepository;

    @Transactional
    public SendReactionResponse sendReaction(String reaction){



        return SendReactionResponse;
    }

}
