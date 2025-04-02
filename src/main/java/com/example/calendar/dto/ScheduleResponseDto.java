package com.example.calendar.dto;

import com.example.calendar.entity.Schedule;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
public class ScheduleResponseDto {
    private final Long id;
    private final String title;
    private final String task;
    private final LocalDateTime createdAt;
    private final LocalDateTime modifiedAt;



    public ScheduleResponseDto(Long id, String title, String task, LocalDateTime createdAt, LocalDateTime modifiedAt) {
        this.id = id;
        this.title = title;
        this.task = task;
        this.createdAt = createdAt;
        this.modifiedAt = modifiedAt;
    }

}
