package com.example.hrback.model.participant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;

@Entity
@Getter
@Setter
@Table(name = "skill_level", schema = "hr_back")
public class SkillLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skill_level_seq")
    @SequenceGenerator(name = "skill_level_seq", initialValue = 1, allocationSize = 1, sequenceName = "skill_level_id_seq")
    @JsonIgnore
    private Long id;
    private String name;
    private String level;
    @ManyToOne
    @JoinColumn(name = "skillset_id")
    @JsonIgnore
    private Skillset skillset;
}