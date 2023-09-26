package com.example.openchatserver.controller;


import com.example.openchatserver.dto.SendReactionResponse;
import com.example.openchatserver.service.ReactionService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReactionController {


    private final ReactionService reactionService;

    @PostMapping("/api/v1/sendReaction")
    public ResponseEntity<SendReactionResponse> updateReaction(@RequestParam(name="reaction")String reaction){

        SendReactionResponse sendReactionResponse = reactionService.sendReaction(reaction);


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sendReactionResponse);
    }


}
