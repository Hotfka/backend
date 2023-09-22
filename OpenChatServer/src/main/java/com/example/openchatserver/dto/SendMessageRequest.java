package com.example.openchatserver.dto;

import lombok.Getter;

@Getter
public class SendMessageRequest {

    private String userName;

    private String text;

    private String sender;


}
