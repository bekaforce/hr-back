package com.example.hrback.model.interview;

import com.example.hrback.model.participant.Participant;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDateTime;

@Entity
@Getter
@Setter
@Table(name = "text", schema = "hr_back")
public class Text {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "text_seq")
    @SequenceGenerator(name = "text_seq", initialValue = 1, allocationSize = 1, sequenceName = "text_id_seq")
    @JsonIgnore
    private Long id;
    private String question;
    @Column(columnDefinition = "TEXT")
    private String text;
    private LocalDateTime sendingTime;
    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "participant_id", referencedColumnName = "id")
    private Participant participant;
    private String comment;
    private Long position;

    public Text(String question, String text, LocalDateTime sendingTime, Participant participant,Long position) {
        this.question = question;
        this.text = text;
        this.sendingTime = sendingTime;
        this.participant = participant;
        this.position = position;
    }

    public Text() {

    }
}
