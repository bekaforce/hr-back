package com.example.hrback.model.participant;

import lombok.Data;

import java.util.Map;

@Data
public class Mail {
    private String templateId;
    private Map<String, Object> paramMap;
    private String subject;
    private String from;
    private String to;
}
