package com.example.hrback.controller.interview;

import com.example.hrback.controller.Url;
import com.example.hrback.service.interview.impl.VideoServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@CrossOrigin
@RestController
@RequestMapping(value = Url.API + Url.VIDEO)
public class VideoController {
    private final VideoServiceImpl videoService;

    public VideoController(VideoServiceImpl videoService) {
        this.videoService = videoService;
    }

    @PostMapping("/upload/{participant_id}")
    public ResponseEntity<?> upload(@RequestParam("multipartFile") MultipartFile multipartFile, @PathVariable(name = "participant_id") Long participant_id, @RequestParam String question, @RequestParam Long position) throws IOException {
        boolean response = videoService.uploadFile(multipartFile, participant_id, question, position);
        return response
                ? new ResponseEntity<>("File was uploaded successfully", HttpStatus.OK)
                : new ResponseEntity<>("File hasn't got any file or couldn't upload it", HttpStatus.BAD_REQUEST);
    }
}
