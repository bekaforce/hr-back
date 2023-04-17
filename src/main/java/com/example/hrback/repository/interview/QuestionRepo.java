package com.example.hrback.repository.interview;

import com.example.hrback.model.interview.Question;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface QuestionRepo extends JpaRepository<Question, Long> {
    @Query(value = "select * from hr_back.question q " +
            "where q.interview = :interview " +
            "and q.vacancy_id = :vacancy_id " +
            "order by q.position", nativeQuery = true)
    List<Question> allByInterview(@Param(value = "interview") String interview, @Param(value = "vacancy_id") Long vacancy_id);

    @Query(value = "select max(q.position) from hr_back.question q where q.interview = :interview and q.vacancy_id = :vacancy_id", nativeQuery = true)
    long maxPosition(@Param("interview") String interview, @Param(value = "vacancy_id") Long vacancy_id);
}
