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
@Table(name = "test", schema = "hr_back")
public class Test {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "test_seq")
    @SequenceGenerator(name = "test_seq", initialValue = 1, allocationSize = 1, sequenceName = "test_id_seq")
    @JsonIgnore
    private Long id;
    private String question;
    private String answer;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "participant_id", referencedColumnName = "id")
    private Participant participant;
    private LocalDateTime dateTime;
    private boolean correct;
    private boolean key;

    public Test(String question, String answer, Participant participant, LocalDateTime dateTime, boolean correct, boolean key) {
        this.question = question;
        this.answer = answer;
        this.participant = participant;
        this.dateTime = dateTime;
        this.correct = correct;
        this.key = key;
    }

    public Test() {

    }
}
