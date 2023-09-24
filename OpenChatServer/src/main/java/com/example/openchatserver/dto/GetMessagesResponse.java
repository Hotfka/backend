package com.example.openchatserver.dto;


import com.example.openchatserver.entity.Message;
import lombok.Getter;

@Getter
public class GetMessagesResponse {

    private Long messageId;

    private String userName;

    private String text;

    private String sender;


    public GetMessagesResponse(Message message) {
        this.messageId = message.getId();
        this.userName = message.getUserName();
        this.text = message.getText();
        this.sender = message.getSender();
    }
}
