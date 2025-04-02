package com.example.calendar.dto;

import lombok.Getter;

@Getter
public class UserRequestDto {
    private final String username;
    private final String email;

    public UserRequestDto(String username, String email) {
        this.username = username;
        this.email = email;
    }
}
