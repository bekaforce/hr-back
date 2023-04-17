package com.example.hrback.dto;

import com.example.hrback.model.participant.Job;
import com.example.hrback.model.participant.Skillset;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.util.List;

@Getter
@Setter
public class ParticipantDto {
    private String name;
    private String phoneNumber;
    private String email;
    private LocalDate birthday;
    private String address;
    private Job job;
    private String education;
    private List<Skillset> skillsets;
    private Long vacancy_id;
}
