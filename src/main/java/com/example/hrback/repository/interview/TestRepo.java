package com.example.hrback.repository.interview;

import com.example.hrback.dto.Dto;
import com.example.hrback.model.interview.Test;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TestRepo extends JpaRepository<Test, Long> {
    @Query(value = "select 'Правильных' as name, count(*) as amount " +
            "from hr_back.test t " +
            "where t.correct = 'true' " +
            "and t.participant_id = :participant_id " +
            "and t.key = 'true' " +
            "union all " +
            "select 'Всего' as name, count(*) as amount " +
            "from hr_back.test t2 " +
            "where t2.participant_id = :participant_id " +
            "and t2.key = 'true'", nativeQuery = true)
    List<Dto> result(@Param(value = "participant_id") Long participant_id);
}
