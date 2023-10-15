package com.example.prod.service.user;

import org.springframework.http.ResponseEntity;

public interface ResponseService {
    ResponseEntity<?> response(String username, String token);
}
