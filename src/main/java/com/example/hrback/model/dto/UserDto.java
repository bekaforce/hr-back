package com.example.hrback.model.dto;

import lombok.Data;

@Data
public class UserDto {
    private String name;
    private String phoneNumber;
    private String email;

    public UserDto(String name, String phoneNumber, String email) {
        this.name = name;
        this.phoneNumber = phoneNumber;
        this.email = email;
    }

    public UserDto() {
    }
}
