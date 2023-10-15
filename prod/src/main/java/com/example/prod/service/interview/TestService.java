package com.example.prod.service.interview;


import com.example.hrback.dto.Dto;
import com.example.hrback.dto.TestDto;

import java.util.List;

public interface TestService {
    String save(List<TestDto> testDtoList, Long participant_id);
    Long percentage(Long participant_id);
    List<Dto> result(Long participant_id);
    String stage(Long participant_id);
}
