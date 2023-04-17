package com.example.hrback.service.interview;

import java.util.List;

public interface InterviewService {
    String chooseTypeOfInterview(Long participantId, String typeOfInterview);
    List<String> getTypesOfInterview();
}
