package com.example.openchatserver.dto;


import com.example.openchatserver.entity.Message;
import com.example.openchatserver.enums.ReactionType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;

@Getter
public class SendReactionRequest {


    private Long messageId;
    private String userName;
    private ReactionType reactionType;


    public SendReactionRequest(String userName, ReactionType reactionType) {
        this.userName = userName;
        this.reactionType = reactionType;
    }
}
