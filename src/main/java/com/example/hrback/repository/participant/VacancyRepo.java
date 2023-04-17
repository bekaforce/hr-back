package com.example.hrback.repository.participant;

import com.example.hrback.model.participant.Vacancy;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VacancyRepo extends JpaRepository<Vacancy, Long> {
    List<Vacancy> findVacancyByActiveIsTrue();
    Vacancy findVacancyById(Long id);
}
