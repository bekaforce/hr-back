package com.example.hrback.service.user;


import com.example.hrback.model.user.User;

public interface UserService {
    boolean sendMessage(String name, String email, String password);
    int generateDigits();
    Long setPassword(String password, Long id);
    User register(String email, String phoneNumber, String password);
    User findByUsername(String userName);
    User findById(Long id);
}
