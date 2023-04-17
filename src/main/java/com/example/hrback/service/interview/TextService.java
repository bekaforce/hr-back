package com.example.hrback.service.interview;

import com.example.hrback.dto.TextDto;
import com.example.hrback.model.interview.Text;

public interface TextService {
    boolean save(TextDto textDto, Long participant_id);
    void add(Text text);
    Long position(Long participant_id);

}

