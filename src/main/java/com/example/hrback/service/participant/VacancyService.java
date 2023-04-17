package com.example.hrback.service.participant;

import com.example.hrback.model.participant.Vacancy;

import java.util.List;

public interface VacancyService {
    List<Vacancy> activeVacancies();
    Vacancy vacancyById(Long id);
}
