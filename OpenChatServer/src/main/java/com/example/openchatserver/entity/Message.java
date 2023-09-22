package com.example.openchatserver.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.sql.Blob;

@NoArgsConstructor
@Getter
@Entity
public class Message extends BaseDateEntity {

    @Id
    @GeneratedValue
    @Column(name="message_id")
    private Long id;

    private String text;

    private String sender;




}
