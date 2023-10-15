package com.example.admin.service.participant.impl;

import com.example.admin.service.participant.MessageService;
import com.example.hrback.model.participant.Message;
import com.example.hrback.repository.participant.MessageRepo;
import org.springframework.stereotype.Service;

@Service
public class MessageServiceImpl implements MessageService {
    private final MessageRepo messageRepo;

    public MessageServiceImpl(MessageRepo messageRepo) {
        this.messageRepo = messageRepo;
    }

    @Override
    public Message save(Message message) {
        return messageRepo.save(message);
    }
}
