package com.example.prod.service.participant;

import com.example.hrback.dto.ParticipantDto;
import com.example.hrback.model.participant.Job;
import com.example.hrback.model.participant.Participant;

import java.time.LocalDateTime;
import java.util.List;

public interface ParticipantService {
    List<Participant> all();
    Participant participantById(Long id);
    Participant setStage(Participant participant, String stage);
    String getStage(Long participant_id);
    Participant participantByEmail(String email);
    boolean expiration(LocalDateTime registration_date);
    Job setJob(Job job);
    boolean sendEmail(String name, String email, String password);
    Participant add(ParticipantDto participantDto);
    Participant save(ParticipantDto participantDto);
}
