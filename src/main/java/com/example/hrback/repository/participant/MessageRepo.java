package com.example.hrback.repository.participant;

import com.example.hrback.model.participant.Message;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface MessageRepo extends JpaRepository<Message, Long> {
    Message findMessageById(Long id);
}
