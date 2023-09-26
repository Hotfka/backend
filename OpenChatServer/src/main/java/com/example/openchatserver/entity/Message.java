package com.example.openchatserver.entity;


import com.example.openchatserver.dto.SendMessageRequest;
import com.example.openchatserver.enums.ReactionType;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.Generated;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.boot.autoconfigure.domain.EntityScan;

import java.sql.Blob;
import java.util.ArrayList;
import java.util.List;

@NoArgsConstructor
@Getter
@Entity
public class Message extends BaseDateEntity {

    @Id
    @GeneratedValue
    @Column(name="message_id")
    private Long id;

    private String userName;

    private String text;

    private String sender;

    @OneToMany(mappedBy = "message",fetch = FetchType.LAZY)
    @JsonManagedReference
    private List<Reaction> reactions = new ArrayList<>();

    public Message(SendMessageRequest request) {
        this.userName = request.getUserName();
        this.text = request.getText();
        this.sender = request.getSender();
    }

    public void setReactions(List<Reaction> reactions) {
        this.reactions = reactions;
    }
}
