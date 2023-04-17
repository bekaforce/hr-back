package com.example.hrback.model.participant;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Getter
@Setter
@Table(name = "vacancy", schema = "hr_back")
public class Vacancy {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "vacancy_seq")
    @SequenceGenerator(name = "vacancy_seq",initialValue = 1, allocationSize = 1, sequenceName = "vacancy_id_seq")
    private Long id;
    private String name;
    @OneToMany(mappedBy = "vacancy")
    @JsonIgnore
    private List<Participant> participants;
    private boolean active;
    private String city;
    @ManyToOne()
    @JoinColumn(name = "group_id", referencedColumnName = "id")
    private Group group;
    @ManyToOne()
    @JoinColumn(name = "skill_id", referencedColumnName = "id")
    @JsonIgnore
    private Skill skill;

}
