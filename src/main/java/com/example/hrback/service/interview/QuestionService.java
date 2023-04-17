package com.example.hrback.service.interview;

import com.example.hrback.model.interview.Question;

import java.util.List;

public interface QuestionService {
    List<Question> test(Long vacancy_id);
    List<Question> interview(Long vacancy_id);
    List<Question> questions(String questionType, Long vacancy_id);
    Long maxPosition(String questionType, Long vacancy_id);
}
