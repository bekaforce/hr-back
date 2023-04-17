package com.example.hrback.repository.interview;

import com.example.hrback.model.interview.Text;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface TextRepo extends JpaRepository<Text, Long> {
    @Query(value = "select max(t.position) from hr_back.text t where t.participant_id = :participant_id", nativeQuery = true)
    Long position(@Param(value = "participant_id") Long participant_id);
}
