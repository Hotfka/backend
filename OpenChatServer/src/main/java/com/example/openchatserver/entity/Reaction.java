package com.example.openchatserver.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import lombok.Getter;

@Entity
@Getter
public class Reaction extends BaseDateEntity{

    @Id
    @GeneratedValue
    @Column(name = "reaction_id")
    private Long id;

    private String userName;







}
