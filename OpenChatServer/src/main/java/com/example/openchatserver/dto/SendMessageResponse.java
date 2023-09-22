package com.example.openchatserver.dto;


import com.example.openchatserver.entity.Message;
import com.example.openchatserver.entity.Reaction;
import jakarta.persistence.Column;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import lombok.Getter;

import java.util.ArrayList;
import java.util.List;

@Getter
public class SendMessageResponse {

    private Long messageId;

    private String userName;

    private String text;

    private String sender;

    private List<Reaction> reactions = new ArrayList<>();

    public SendMessageResponse(Message message) {
        this.messageId = message.getId();
        this.userName = message.getUserName();
        this.text = message.getText();
        this.sender = message.getSender();
        this.reactions = message.getReactions();
    }
}
