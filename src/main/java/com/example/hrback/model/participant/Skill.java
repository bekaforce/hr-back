package com.example.hrback.model.participant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Setter
@Getter
@Table(name = "skill", schema = "hr_back")
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skill_seq")
    @SequenceGenerator(name = "skill_seq", initialValue = 1, allocationSize = 1, sequenceName = "skill_id_seq")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "skill", cascade = CascadeType.REMOVE)
    @JsonIgnore
    private List<Vacancy> vacancies;
    @OneToMany(mappedBy = "skill", cascade = CascadeType.REMOVE)
    private List<Subject> subjects;
}
