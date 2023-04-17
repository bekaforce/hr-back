package com.example.hrback.service.interview.impl;

import com.example.hrback.dto.Dto;
import com.example.hrback.dto.TestDto;
import com.example.hrback.model.interview.Test;
import com.example.hrback.model.participant.Participant;
import com.example.hrback.repository.interview.TestRepo;
import com.example.hrback.service.interview.TestService;
import com.example.hrback.service.participant.impl.ParticipantServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.List;

@Service
public class TestServiceImpl implements TestService {
    private final TestRepo testRepo;
    private final ParticipantServiceImpl participantService;

    public TestServiceImpl(TestRepo testRepo, ParticipantServiceImpl participantService) {
        this.testRepo = testRepo;
        this.participantService = participantService;
    }

    @Override
    public String save(List<TestDto> testDtoList, Long participant_id) {
        Participant participant = participantService.participantById(participant_id);
        if (participant != null){
            testDtoList.forEach(testDto -> {
                LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Bishkek"));
                Test test = new Test(testDto.getQuestion(), testDto.getAnswer(), participant, now, testDto.isCorrect(), testDto.isKey());
                testRepo.save(test);
        });
            participantService.setStage(participant, "interview");
        return stage(participant_id);
    }
        return null;
    }

    @Override
    public Long percentage(Long participant_id) {
        List<Dto> result = result(participant_id);
        Long correct = result.get(0).getAmount();;
        Long all = result.get(1).getAmount();
        double number = (double) correct / all * 100;
        return Math.round(number);
    }

    @Override
    public List<Dto> result(Long participant_id) {
        return testRepo.result(participant_id);
    }

    @Override
    public String stage(Long participant_id) {
        Participant participant = participantService.participantById(participant_id);
        if (percentage(participant_id) < 70){
            participantService.setStage(participantService.participantById(participant_id), "failed");
            return participant.getStage();
        }
        return participant.getStage();
    }


}
