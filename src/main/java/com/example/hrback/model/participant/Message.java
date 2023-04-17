package com.example.hrback.model.participant;

import lombok.Data;

import javax.persistence.*;

@Entity
@Data
@Table(name = "message", schema = "hr_back")
public class Message {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    private String name;
    private String text;
}
