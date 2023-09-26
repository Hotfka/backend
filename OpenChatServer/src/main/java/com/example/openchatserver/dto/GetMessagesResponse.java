package com.example.openchatserver.dto;


import com.example.openchatserver.entity.Message;
import com.example.openchatserver.entity.Reaction;
import lombok.Getter;

import java.util.List;

@Getter
public class GetMessagesResponse {

    private Long messageId;

    private String userName;

    private String text;

    private String sender;

    private List<Reaction> reactions;


    public GetMessagesResponse(Message message) {
        this.messageId = message.getId();
        this.userName = message.getUserName();
        this.text = message.getText();
        this.sender = message.getSender();
        this.reactions = message.getReactions();

    }
}
