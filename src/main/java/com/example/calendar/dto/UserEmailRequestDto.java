package com.example.calendar.dto;

import lombok.Getter;

@Getter
public class UserEmailRequestDto {
    private String email;

    public UserEmailRequestDto(String email){
        this.email = email;
    }
}
