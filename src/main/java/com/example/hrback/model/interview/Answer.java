package com.example.hrback.model.interview;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "answer", schema = "hr_back")
public class Answer {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "answer_seq")
    @SequenceGenerator(name = "answer_seq", initialValue = 1, allocationSize = 1, sequenceName = "answer_id_seq")
    private Long id;
    private String content;
    @JsonIgnore
    @ManyToOne()
    @JoinColumn(name = "question_id", referencedColumnName = "id")
    private Question question;
    private boolean correct;
}
