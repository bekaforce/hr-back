package com.example.hrback.service.interview.impl;

import com.example.hrback.dto.TextDto;
import com.example.hrback.model.interview.Interview;
import com.example.hrback.model.interview.Text;
import com.example.hrback.model.participant.Participant;
import com.example.hrback.repository.interview.TextRepo;
import com.example.hrback.service.interview.TextService;
import com.example.hrback.service.participant.impl.ParticipantServiceImpl;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
public class TextServiceImpl implements TextService {
    private final ParticipantServiceImpl participantService;
    private final TextRepo textRepo;
    private final QuestionServiceImpl questionService;

    public TextServiceImpl(ParticipantServiceImpl participantService, TextRepo textRepo, QuestionServiceImpl questionService) {
        this.participantService = participantService;
        this.textRepo = textRepo;
        this.questionService = questionService;
    }


    @Override
    public boolean save(TextDto textDto, Long participant_id) {
        Participant participant = participantService.participantById(participant_id);
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Bishkek"));
        if (participant != null){
            Text text = new Text(textDto.getQuestion(), textDto.getText(), now, participant, textDto.getPosition());
            add(text);
            return true;
        }
        return false;
    }

    @Override
    public void add(Text text) {
        Long candidateType_id = text.getParticipant().getVacancy().getId();
        Long maxPosition = questionService.maxPosition(Interview.INTERVIEW.toString(), candidateType_id);
        if (text.getPosition() >= maxPosition){
            participantService.setStage(text.getParticipant(), "completed");
        }
        textRepo.save(text);
    }

    @Override
    public Long position(Long participant_id) {
        Long position = textRepo.position(participant_id);
        if (position != null){
            return position;
        }
        return 0L;
    }
}
