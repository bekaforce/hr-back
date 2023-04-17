package com.example.hrback.controller.interview;

import com.example.hrback.controller.Url;
import com.example.hrback.service.interview.impl.InterviewServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Url.API + Url.INTERVIEW)
public class InterviewController {
    private final InterviewServiceImpl interviewService;

    public InterviewController(InterviewServiceImpl interviewService) {
        this.interviewService = interviewService;
    }

    @PostMapping(Url.CHOOSE + Url.TYPE_OF_INTERVIEW + Url.PARTICIPANT_ID)
    public ResponseEntity<?> chooseTypeOfInterview(@RequestParam String typeOfInterview, @PathVariable(value = "participant_id") Long participant_id){
        String response = interviewService.chooseTypeOfInterview(participant_id, typeOfInterview);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping(Url.ALL + Url.TYPE_OF_INTERVIEW)
    public ResponseEntity<?> getTypesOfInterview(){
        List<String> response = interviewService.getTypesOfInterview();
        return response != null && !response.isEmpty()
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }


}
