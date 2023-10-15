package com.example.prod.controller.participant;

import com.example.hrback.model.participant.Vacancy;
import com.example.prod.controller.Url;
import com.example.prod.service.participant.impl.VacancyServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(Url.API + Url.VACANCY)
@CrossOrigin
public class VacancyController {
    private final VacancyServiceImpl vacancyService;

    public VacancyController(VacancyServiceImpl vacancyService) {
        this.vacancyService = vacancyService;
    }

    @GetMapping(Url.ALL_ACTIVE)
    public ResponseEntity<?> allActive(){
        List<Vacancy> vacancies = vacancyService.activeVacancies();
        return vacancies != null && !vacancies.isEmpty()
                ? new ResponseEntity<>(vacancies, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }
}
