package com.example.hrback.service.user;

import org.springframework.http.ResponseEntity;

public interface ResponseService {
    ResponseEntity<?> response(String username, String token);
}
