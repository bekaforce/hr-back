package com.example.hrback.model.participant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Getter
@Setter
@Table(name = "job", schema = "hr_back")
public class Job {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "job_seq")
    @SequenceGenerator(name = "job_seq", initialValue = 1, allocationSize = 1, sequenceName = "job_id_seq")
    @JsonIgnore
    private Long id;
    private String name;
    private String employer;
    private LocalDate beginningDate;
    private LocalDate endDate;
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL, mappedBy = "job")
    private Participant participant;
}

