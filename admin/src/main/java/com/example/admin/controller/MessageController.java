package com.example.admin.controller;

import com.example.admin.service.participant.impl.MessageServiceImpl;
import com.example.hrback.model.interview.Question;
import com.example.hrback.model.participant.Message;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping("message")
@CrossOrigin
public class MessageController {
    private final MessageServiceImpl messageService;

    public MessageController(MessageServiceImpl messageService) {
        this.messageService = messageService;
    }

    @PostMapping("/save")
    public ResponseEntity<?> save(@RequestBody Message message) {
        Message response = messageService.save(message);
        return response != null
                ? new ResponseEntity<>(response, HttpStatus.OK)
                : new ResponseEntity<>("Bad request", HttpStatus.BAD_REQUEST);
    }
}
