package com.example.hrback.repository.participant;

import com.example.hrback.model.participant.Participant;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface ParticipantRepo extends JpaRepository<Participant, Long> {
    Participant findParticipantById(Long id);

    @Query(value = "select * from hr_back.participant c " +
            "where c.email = :email " +
            "ORDER BY registration_date DESC " +
            "FETCH FIRST 1 ROWS WITH TIES", nativeQuery = true)
    Participant findParticipantByEmail(@Param(value = "email") String email);
}
