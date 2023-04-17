package com.example.hrback.service.interview.impl;

import com.example.hrback.model.interview.Interview;
import com.example.hrback.model.interview.Video;
import com.example.hrback.model.participant.Participant;
import com.example.hrback.repository.interview.VideoRepo;
import com.example.hrback.service.interview.VideoService;
import com.example.hrback.service.participant.impl.ParticipantServiceImpl;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.util.UUID;

@Service
public class VideoServiceImpl implements VideoService {
    private final VideoRepo videoRepo;
    private final ParticipantServiceImpl participantService;
    private final QuestionServiceImpl questionService;

    public VideoServiceImpl(VideoRepo videoRepo, ParticipantServiceImpl participantService, QuestionServiceImpl questionService) {
        this.videoRepo = videoRepo;
        this.participantService = participantService;
        this.questionService = questionService;
    }

    @Value("${server-config.upload-path}")
    private String UPLOADED_FOLDER;

    @Override
    public boolean uploadFile(MultipartFile multipartFile, Long participant_id, String question, Long position) throws IOException {
        if (multipartFile != null && !multipartFile.toString().equals("")){
            File uploadDir = new File(UPLOADED_FOLDER);
            String uuidFile = UUID.randomUUID().toString();
            String filename = uuidFile + multipartFile.getOriginalFilename();
            multipartFile.transferTo(new File(uploadDir + "/" + filename));
            LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Bishkek"));
            Participant participant = participantService.participantById(participant_id);
            Video video = new Video(filename, now, question, participant, null, position);
            save(video);
            return true;
        }
        else {
            return false;
        }
    }

    @Override
    public Long position(Long participant_id) {
        Long position = videoRepo.position(participant_id);
        if (position != null){
            return position;
        }
        return 0L;
    }

    @Override
    public Video save(Video video) {
        Long participant_id = video.getParticipant().getVacancy().getId();
        Long maxPosition = questionService.maxPosition(Interview.INTERVIEW.toString(), participant_id);
        if (video.getPosition() >= maxPosition){
            participantService.setStage(video.getParticipant(), "completed");
        }
        return videoRepo.save(video);
    }
}
