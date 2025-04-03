package com.example.calendar.dto;

import lombok.Getter;

@Getter
public class ScheduleUpdateRequestDto {
    private String title;
    private String task;

    public ScheduleUpdateRequestDto(String title, String task) {
        this.title = title;
        this.task = task;
    }
}
