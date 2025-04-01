package com.example.calendar.dto;

import lombok.Getter;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String title;
    private final String task;


    public ScheduleResponseDto(Long id, String title, String task) {
        this.id = id;
        this.title = title;
        this.task = task;
    }
}
