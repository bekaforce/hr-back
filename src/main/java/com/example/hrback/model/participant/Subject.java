package com.example.hrback.model.participant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "subject", schema = "hr_back")
public class Subject {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "subject_seq")
    @SequenceGenerator(name = "subject_seq", initialValue = 1, allocationSize = 1, sequenceName = "subject_id_seq")
    private Long id;
    private String name;
    @ManyToOne()
    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    @JsonIgnore
    private Skill skill;
    @OneToMany(mappedBy = "subject", cascade = CascadeType.REMOVE)
    private List<Degree> degrees;

}
