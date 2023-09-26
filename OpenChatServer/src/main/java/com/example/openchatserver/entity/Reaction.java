package com.example.openchatserver.entity;


import com.example.openchatserver.dto.SendReactionRequest;
import com.example.openchatserver.enums.ReactionType;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.Getter;
import lombok.NoArgsConstructor;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
@NoArgsConstructor
public class Reaction extends BaseDateEntity {

    @Id
    @GeneratedValue
    @Column(name = "reaction_id")
    private Long id;

    private String userName;

    @Enumerated(EnumType.STRING)
    private ReactionType reactionType;


    @ManyToOne(fetch = LAZY)
    @JoinColumn(name="message_id")
    @JsonBackReference
    private Message message;

    public Reaction(SendReactionRequest request) {
        this.userName = request.getUserName();
        this.reactionType = request.getReactionType();
    }

    public void setMessage(Message message) {
        this.message = message;
    }
}