package com.example.openchatserver.controller;


import com.example.openchatserver.dto.SendMessageRequest;
import com.example.openchatserver.dto.SendMessageResponse;
import com.example.openchatserver.service.MessageService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequiredArgsConstructor
public class ReactionController {


    private final ReactionService reactionService;

    @PutMapping("/api/v1/updateReaction")
    public ResponseEntity<updateReactionResponse> updateReaction(@RequestParam(name="reaction")String reaction){

        updateReactionResponse updateReactionResponse = reactionService.updateReaction();


        return ResponseEntity.status(HttpStatus.CREATED)
                .body(updateReactionResponse);
    }


}
