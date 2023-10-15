package com.example.prod.controller.participant;


import com.example.hrback.dto.ParticipantDto;
import com.example.hrback.model.participant.Participant;
import com.example.prod.controller.Url;
import com.example.prod.service.participant.impl.ParticipantServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping(Url.API + Url.PARTICIPANT)
@CrossOrigin
public class ParticipantController {
    private final ParticipantServiceImpl participantService;

    public ParticipantController(ParticipantServiceImpl participantService) {
        this.participantService = participantService;
    }

    @PostMapping(Url.SAVE)
    public ResponseEntity<?> save(@RequestBody ParticipantDto participantDto){
        Participant participant = participantService.save(participantDto);
        return participant != null
                ? new ResponseEntity<>(participant, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @GetMapping(Url.ALL)
    public ResponseEntity<?> all(){
        List<Participant> all = participantService.all();
        return all != null && !all.isEmpty()
                ? new ResponseEntity<>(all, HttpStatus.OK)
                : new ResponseEntity<>("Not found", HttpStatus.NOT_FOUND);
    }

    @PostMapping("email-test")
    public ResponseEntity<?> email_test(@RequestParam String name, @RequestParam String email, @RequestParam String password){
        participantService.sendEmail(name, email, password);
        return new ResponseEntity<>(HttpStatus.OK);
    }

}
