package com.example.hrback.controller.interview;

import com.example.hrback.controller.Url;
import com.example.hrback.dto.TextDto;
import com.example.hrback.service.interview.impl.TextServiceImpl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;


@RestController
@RequestMapping(Url.API + Url.TEXT)
public class TextController {
    private final TextServiceImpl textService;

    public TextController(TextServiceImpl textService) {
        this.textService = textService;
    }

    @PostMapping(Url.SAVE + "/{participant_id}")
    public ResponseEntity<?> save(@RequestBody TextDto textDto, @PathVariable(value = "participant_id") Long participant_id){
        boolean response = textService.save(textDto, participant_id);
        return response
                ? new ResponseEntity<>("Text was saved successfully", HttpStatus.OK)
                : new ResponseEntity<>("Text couldn't be saved", HttpStatus.BAD_REQUEST);
    }
}
