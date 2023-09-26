package com.example.openchatserver.dto;


import com.example.openchatserver.entity.Message;
import com.example.openchatserver.enums.ReactionType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
public class SendReactionResponse {


    private Long messageId;

    private String userName;

    private ReactionType reactionType;

    public SendReactionResponse(SendReactionRequest request) {
        this.messageId = request.getMessageId();
        this.userName = request.getUserName();
        this.reactionType = request.getReactionType();
    }
}
