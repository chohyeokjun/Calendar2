package com.example.calendar.dto;

import lombok.Getter;

@Getter
public class ScheduleRequestDto {
    private final String username;
    private final String title;
    private final String task;

    public ScheduleRequestDto(String username, String title, String task) {
        this.username = username;
        this.title = title;
        this.task = task;
    }
}
