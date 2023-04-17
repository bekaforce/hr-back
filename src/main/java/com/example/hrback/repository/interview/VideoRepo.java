package com.example.hrback.repository.interview;

import com.example.hrback.model.interview.Video;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface VideoRepo extends JpaRepository<Video, Long> {
    @Query(value = "select max(v.position) from hr_back.video v where v.participant_id = :participant_id", nativeQuery = true)
    Long position(@Param(value = "participant_id") Long candidate_id);
}
