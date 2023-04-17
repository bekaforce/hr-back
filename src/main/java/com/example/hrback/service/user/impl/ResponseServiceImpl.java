package com.example.hrback.service.user.impl;

import com.example.hrback.model.participant.Participant;
import com.example.hrback.service.interview.impl.TextServiceImpl;
import com.example.hrback.service.interview.impl.VideoServiceImpl;
import com.example.hrback.service.participant.impl.ParticipantServiceImpl;
import com.example.hrback.service.user.ResponseService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.Map;

@Service
public class ResponseServiceImpl implements ResponseService {
    private final ParticipantServiceImpl participantService;
    private final VideoServiceImpl videoService;
    private final TextServiceImpl textService;

    public ResponseServiceImpl(ParticipantServiceImpl participantService, VideoServiceImpl videoService, TextServiceImpl textService) {
        this.participantService = participantService;
        this.videoService = videoService;
        this.textService = textService;
    }

    @Override
    public ResponseEntity<?> response(String username, String token) {
        Map<Object, Object> response = new HashMap<>();
        response.put("username", username);
        response.put("token", token);
        Participant participant = participantService.participantByEmail(username);
        response.put("stage", participantService.getStage(participant.getId()));
        response.put("VacancyId", participant.getVacancy().getId());
        response.put("VacancyName", participant.getVacancy().getName());
        response.put("participantId", participant.getId());
        String stage = participantService.getStage(participant.getId());
        if (stage.contains("Video")){
            Long index = videoService.position(participant.getId());
            response.put("index", index);
        }
        if (stage.contains("test")){
            response.put("index", 0);
        }
        if (stage.contains("text")){
            Long index = textService.position(participant.getId());
            response.put("index", index);
        }
        return ResponseEntity.ok(response);
    }
}
