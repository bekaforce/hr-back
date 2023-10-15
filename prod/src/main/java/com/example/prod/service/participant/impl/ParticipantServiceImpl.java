package com.example.prod.service.participant.impl;

import com.example.hrback.dto.ParticipantDto;
import com.example.hrback.model.participant.Job;
import com.example.hrback.model.participant.Participant;
import com.example.hrback.repository.participant.ParticipantRepo;
import com.example.prod.service.participant.ParticipantService;
import com.example.prod.service.user.impl.UserServiceImpl;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class ParticipantServiceImpl implements ParticipantService {
    private final ParticipantRepo participantRepo;
    private final VacancyServiceImpl vacancyService;
    private final MessageServiceImpl messageService;
    private final UserServiceImpl userService;
    private final BCryptPasswordEncoder passwordEncoder;

    public ParticipantServiceImpl(ParticipantRepo participantRepo, VacancyServiceImpl vacancyService, MessageServiceImpl messageService, UserServiceImpl userService, BCryptPasswordEncoder passwordEncoder) {
        this.participantRepo = participantRepo;
        this.vacancyService = vacancyService;
        this.messageService = messageService;
        this.userService = userService;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public List<Participant> all() {
        return participantRepo.findAll();
    }

    @Override
    public Participant participantById(Long id) {
        return participantRepo.findParticipantById(id);
    }

    @Override
    public Participant setStage(Participant participant, String stage) {
        participant.setStage(stage);
        return participantRepo.save(participant);
    }

    @Override
    public String getStage(Long participant_id) {
        Participant participant = participantById(participant_id);
        if (participant != null){
            return participant.getStage();
        }
        else {
            return "Срок истек";
        }
    }

    @Override
    public Participant participantByEmail(String email) {
        return participantRepo.findParticipantByEmail(email);
    }

    @Override
    public boolean expiration(LocalDateTime registration_date) {
        long daysToExpire = 2L;
        LocalDateTime expirationDate = registration_date.plusDays(daysToExpire);
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Bishkek"));
        return now.compareTo(expirationDate) > 0;
    }

    @Override
    public Job setJob(Job job) {
        if (job.getName().isEmpty()){
            job.setName("Нет опыта");
            job.setEndDate(null);
            job.setBeginningDate(null);
            job.setName("");
        }
        return job;
    }

    @Override
    public boolean sendEmail(String name, String email, String password) {
        return messageService.sendEmail(name, email, password);
    }

    @Override
    public Participant add(ParticipantDto participantDto) {
        Participant participant = new Participant();
        participant.setName(participantDto.getName());
        participant.setRegistrationDate(LocalDateTime.now(ZoneId.of("Asia/Bishkek")));
        participant.setAddress(participantDto.getAddress());
        participant.setBirthday(participantDto.getBirthday());
        participant.setEducation(participantDto.getEducation());
        participant.setPhoneNumber(participantDto.getPhoneNumber());
        participant.setJob(participantDto.getJob());
        participant.setComment(null);
        participant.setEmail(participantDto.getEmail());
        participant.setVacancy(vacancyService.vacancyById(participantDto.getVacancy_id()));
        participant.setStage("test");
        participant.setSkillsets(participantDto.getSkillsets());
        return participantRepo.save(participant);
    }


        @Override
        public Participant save(ParticipantDto participantDto) {
            String password = String.valueOf(userService.generateDigits());
            Participant participant = add(participantDto);
            if (participant != null){
                String encoded = passwordEncoder.encode(password);
                userService.register(participant.getEmail(), participant.getPhoneNumber(), encoded);
                userService.sendMessage(participantDto.getName(), participantDto.getEmail(), password);
            }
            return participant;
    }
}
