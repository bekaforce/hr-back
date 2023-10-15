package com.example.prod.service.interview.impl;

import com.example.hrback.model.participant.Participant;
import com.example.prod.service.interview.InterviewService;
import com.example.prod.service.participant.impl.ParticipantServiceImpl;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class InterviewServiceImpl implements InterviewService {
    private final ParticipantServiceImpl participantService;

    public InterviewServiceImpl(ParticipantServiceImpl participantService) {
        this.participantService = participantService;
    }

    @Override
    public String chooseTypeOfInterview(Long participantId, String typeOfInterview) {
        Participant participant = participantService.participantById(participantId);
        if (participant != null){
            participantService.setStage(participant, typeOfInterview);
            return typeOfInterview;
        }
        return null;
    }

    @Override
    public List<String> getTypesOfInterview() {
        List<String> result = new ArrayList<>();
        result.add("test");
        result.add("video");
        return result;
    }
}
