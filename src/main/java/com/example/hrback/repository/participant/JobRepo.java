package com.example.hrback.repository.participant;

import com.example.hrback.model.participant.Job;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface JobRepo extends JpaRepository<Job, Long> {

}
