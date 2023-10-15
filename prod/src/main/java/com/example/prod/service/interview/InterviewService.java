package com.example.prod.service.interview;

import java.util.List;

public interface InterviewService {
    String chooseTypeOfInterview(Long participantId, String typeOfInterview);
    List<String> getTypesOfInterview();
}
