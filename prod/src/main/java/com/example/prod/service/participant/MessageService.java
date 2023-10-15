package com.example.prod.service.participant;

import com.example.hrback.dto.FinalDto;
import com.example.hrback.model.participant.Mail;
import com.example.hrback.model.participant.Message;

public interface MessageService {
    Message messageById(Long id);
    String getText(Long id);
    boolean sendEmail(String name, String email, String password);
    String setMessage(String name, String password, Long message_id);
    boolean sendMessage(Mail mail);
    String setSuccessText(String name, String vacancy, Long message_id);
    FinalDto success(String name, String vacancy);
    Message save(Message message);
}
