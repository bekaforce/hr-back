package com.example.hrback.service.interview.impl;

import com.example.hrback.model.interview.Interview;
import com.example.hrback.model.interview.Question;
import com.example.hrback.repository.interview.QuestionRepo;
import com.example.hrback.service.interview.QuestionService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class QuestionServiceImpl implements QuestionService {
    private final QuestionRepo questionRepo;

    public QuestionServiceImpl(QuestionRepo questionRepo) {
        this.questionRepo = questionRepo;
    }

    @Override
    public List<Question> test(Long vacancy_id) {
        return questions(Interview.TEST.toString(), vacancy_id);
    }

    @Override
    public List<Question> interview(Long vacancy_id) {
        return questions(Interview.INTERVIEW.toString(), vacancy_id);
    }

    @Override
    public List<Question> questions(String interview, Long vacancy_id) {
        return questionRepo.allByInterview(interview, vacancy_id);
    }

    @Override
    public Long maxPosition(String interview, Long vacancy_id) {
        return questionRepo.maxPosition(interview, vacancy_id);
    }
}
