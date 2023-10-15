package com.example.prod.controller.interview;

import com.example.hrback.model.interview.Question;
import com.example.prod.controller.Url;
import com.example.prod.service.interview.impl.QuestionServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@CrossOrigin
@RestController
@RequestMapping(value = Url.API + Url.QUESTION)
public class QuestionController {
    private final QuestionServiceImpl questionService;

    public QuestionController(QuestionServiceImpl questionService) {
        this.questionService = questionService;
    }

    @GetMapping("/forTest/{vacancy_id}")
    public ResponseEntity<?> questionsForTest(@PathVariable(name = "vacancy_id") Long vacancy_id) {
        List<Question> response = questionService.test(vacancy_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @GetMapping("/forInterview/{vacancy_id}")
    public ResponseEntity<?> questionsForInterview(@PathVariable(name = "vacancy_id") Long vacancy_id){
        List<Question> response = questionService.interview(vacancy_id);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }
}
