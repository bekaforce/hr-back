package com.example.prod.service.interview;

import com.example.hrback.model.interview.Video;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

public interface VideoService {
    boolean uploadFile(MultipartFile multipartFile, Long participant_id, String question, Long position) throws IOException;
    Long position(Long participant_id);
    Video save(Video video);
}
