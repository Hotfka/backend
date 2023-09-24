package com.example.openchatserver.dto;


import lombok.Getter;

@Getter
public class GetMessagesResponse {

    private Long messageId;

    private String userName;

    private String text;

    private String sender;

}
