package com.example.hrback.controller.interview;

import com.example.hrback.controller.Url;
import com.example.hrback.dto.TestDto;
import com.example.hrback.model.participant.Vacancy;
import com.example.hrback.service.interview.impl.TestServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping(Url.API + Url.TEST)
public class TestController {
private final TestServiceImpl testService;

    public TestController(TestServiceImpl testService) {
        this.testService = testService;
    }

    @PostMapping(Url.SAVE + "/{participant_id}")
    public ResponseEntity<?> save(@RequestBody List<TestDto> testDtoList, @PathVariable(value = "participant_id") Long participant_id){
        String response = testService.save(testDtoList, participant_id);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

}
