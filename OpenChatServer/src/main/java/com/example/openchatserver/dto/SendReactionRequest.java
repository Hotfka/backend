package com.example.openchatserver.dto;


import com.example.openchatserver.entity.Message;
import com.example.openchatserver.enums.ReactionType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class SendReactionRequest {


    private Long messageId;
    private String userName;
    private ReactionType reactionType;


    public SendReactionRequest(Long messageId, String userName, ReactionType reactionType) {
        this.messageId = messageId;
        this.userName = userName;
        this.reactionType = reactionType;
    }
}
