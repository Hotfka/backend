package com.example.openchatserver.controller;


import com.example.openchatserver.dto.SendReactionRequest;
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
    public ResponseEntity<SendReactionResponse> sendReaction(@RequestBody SendReactionRequest request){

        SendReactionResponse sendReactionResponse = reactionService.sendReaction(request);


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(sendReactionResponse);
    }


}
