package com.example.hrback.model.participant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "degree", schema = "hr_back")
public class Degree {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "degree_seq")
    @SequenceGenerator(name = "degree_seq", initialValue = 1, allocationSize = 1, sequenceName = "degree_id_seq")
    private Long id;
    private String name;
    @ManyToOne()
    @JoinColumn(name = "subject_id", referencedColumnName = "id")
    @JsonIgnore
    private Subject subject;
}
