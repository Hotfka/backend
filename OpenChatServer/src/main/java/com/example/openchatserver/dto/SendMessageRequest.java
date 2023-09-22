package com.example.openchatserver.dto;

import lombok.Getter;

@Getter
public class SendMessageRequest {

    private Long messageId;

    private String userName;

    private String text;

    private String sender;


}
