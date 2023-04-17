package com.example.hrback.model.participant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "skillset", schema = "hr_back")
public class Skillset {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "skillset_seq")
    @SequenceGenerator(name = "skillset_seq", initialValue = 1, allocationSize = 1, sequenceName = "skillset_id_seq")
    @JsonIgnore
    private Long id;
    private String name;
    @ManyToOne()
    @JoinColumn(name = "participant_id")
    @JsonIgnore
    private Participant participant;
    @OneToMany(cascade = CascadeType.ALL)
    @JoinColumn(name = "skillset_id")
    private List<SkillLevel> skillLevels;
}
