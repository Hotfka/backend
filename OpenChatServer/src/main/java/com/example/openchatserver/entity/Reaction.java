package com.example.openchatserver.entity;


import com.example.openchatserver.enums.ReactionType;
import jakarta.persistence.*;
import lombok.Getter;

import static jakarta.persistence.FetchType.LAZY;

@Entity
@Getter
public class Reaction extends BaseDateEntity {

    @Id
    @GeneratedValue
    @Column(name = "reaction_id")
    private Long id;

    private String userName;

    private ReactionType reactionType;

    @ManyToOne
    @JoinColumn(name="message_id")
    private Message message;


}