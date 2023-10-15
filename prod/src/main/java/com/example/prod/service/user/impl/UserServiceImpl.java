package com.example.prod.service.user.impl;

import com.example.prod.model.user.Status;
import com.example.prod.model.user.User;
import com.example.prod.repository.user.UserRepo;
import com.example.prod.service.participant.impl.MessageServiceImpl;
import com.example.prod.service.user.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.ZoneId;

@Service
@Slf4j
public class UserServiceImpl implements UserService {
    private final UserRepo userRepo;
    private final MessageServiceImpl messageService;

    public UserServiceImpl(UserRepo userRepo, MessageServiceImpl messageService) {
        this.userRepo = userRepo;
        this.messageService = messageService;
    }

    @Override
    public boolean sendMessage(String name, String email, String password) {
        return messageService.sendEmail(name, email, password);
    }

    @Override
    public int generateDigits() {
        int max = 9999;
        int min = 1000;
        return (int) (min + Math.random() * (max + min));
    }

    @Override
    public Long setPassword(String password, Long id) {
        String pattern = "^(?=.*[0-9])(?=.*[a-z])(?=.*[A-Z])(?=.*[@#$%^&+=])(?=\\S+$).{8,}$";
        if (password.matches(pattern)){
            User user = findById(id);
            user.setPassword(password);
            return id;
        }
        else {
            return null;
        }
    }

    @Override
    public User register(String email, String phoneNumber, String password) {
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Bishkek"));
        User user = findByUsername(email);
        if (user != null){
            user.setPassword(password);
        }
        else {
            user = new User();
            user.setUsername(email);
            user.setPassword(password);
        }
        user.setStatus(Status.ACTIVE);
        user.setPhoneNumber(phoneNumber);
        user.setDate(now);
        return userRepo.save(user);
    }

    @Override
    public User findByUsername(String userName) {
        return userRepo.findByUsername(userName);
    }

    @Override
    public User findById(Long id)  {
        return userRepo.findById(id).orElse(null);
    }
}
