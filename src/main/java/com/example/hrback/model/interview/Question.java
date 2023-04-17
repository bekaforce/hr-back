package com.example.hrback.model.interview;

import com.example.hrback.model.participant.Vacancy;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "question", schema = "hr_back")
public class Question {
    @Id
    @GeneratedValue(strategy = javax.persistence.GenerationType.SEQUENCE, generator = "question_seq")
    @SequenceGenerator(name = "question_seq", initialValue = 1, allocationSize = 1, sequenceName = "question_id_seq")
    private Long id;
    private String text;
    @OneToMany(mappedBy = "question", cascade = CascadeType.REMOVE)
    private List<Answer> answers;
    @Enumerated(value = EnumType.STRING)
    private Interview interview;
    private Long position;
    @JsonIgnore
    @ManyToOne(targetEntity = Vacancy.class)
    @JoinColumn(name = "vacancy_id", referencedColumnName = "id")
    private Vacancy vacancy;
    private Long milliseconds;
    private boolean key;


    public Question(Long id, String text, Interview interview, Long position, Vacancy vacancy, Long milliseconds, boolean key) {
        this.id = id;
        this.text = text;
        this.interview = interview;
        this.position = position;
        this.vacancy = vacancy;
        this.milliseconds = milliseconds;
        this.key = key;
    }

    public Question(String text, Interview interview, Long position, Vacancy vacancy, Long milliseconds, boolean key) {
        this.text = text;
        this.interview = interview;
        this.position = position;
        this.vacancy = vacancy;
        this.milliseconds = milliseconds;
        this.key = key;
    }

    public Question() {

    }
}
