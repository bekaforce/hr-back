package com.example.prod.service.participant.impl;

import com.example.hrback.model.participant.Vacancy;
import com.example.hrback.repository.participant.VacancyRepo;
import com.example.prod.service.participant.VacancyService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VacancyServiceImpl implements VacancyService {
    private final VacancyRepo vacancyRepo;

    public VacancyServiceImpl(VacancyRepo vacancyRepo) {
        this.vacancyRepo = vacancyRepo;
    }


    @Override
    public List<Vacancy> activeVacancies() {
        return vacancyRepo.findVacancyByActiveIsTrue();
    }

    @Override
    public Vacancy vacancyById(Long id) {
        return vacancyRepo.findVacancyById(id);
    }
}
