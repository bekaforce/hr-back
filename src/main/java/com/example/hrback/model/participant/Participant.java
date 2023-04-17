package com.example.hrback.model.participant;

import com.example.hrback.model.interview.Test;
import com.example.hrback.model.interview.Text;
import com.example.hrback.model.interview.Video;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "participant", schema = "hr_back")
public class Participant {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "participant_seq")
    @SequenceGenerator(name = "participant_seq", initialValue = 1, allocationSize = 1, sequenceName = "participant_id_seq")
    private Long id;
    private String name;
    @ManyToOne
    @JoinColumn(name = "vacancy_id", referencedColumnName = "id")
    private Vacancy vacancy;
    private String phoneNumber;
    private String email;
    private LocalDate birthday;
    private String address;
    private LocalDateTime registrationDate;
    @OneToOne(cascade = CascadeType.ALL)
    @JoinColumn(name = "job_id", referencedColumnName = "id")
    private Job job;
    private String education;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "participant_id")
    private List<Skillset> skillsets;
    private String comment;
    private String stage;
    @OneToMany(mappedBy = "participant", cascade = CascadeType.REMOVE)
    private List<Text> texts;
    @OneToMany(mappedBy = "participant", cascade = CascadeType.REMOVE)
    private List<Test> tests;
    @OneToMany(mappedBy = "participant", cascade = CascadeType.REMOVE)
    private List<Video> videos;
}
