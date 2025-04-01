package com.example.calendar.controller;

import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;
import com.example.calendar.entity.Schedule;
import com.example.calendar.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/schedules")
@RequiredArgsConstructor
public class ScheduleController {

    // 서비스 접근
    private final ScheduleService scheduleService;

    // 일정 생성
    @PostMapping
    public ResponseEntity<ScheduleResponseDto> saveSchedule (@RequestBody ScheduleRequestDto requestDto) {
        ScheduleResponseDto saveSchedule = scheduleService.saveSchedule(requestDto.getUsername(), requestDto.getTitle(), requestDto.getTask());
        return new ResponseEntity<>(saveSchedule, HttpStatus.CREATED);
    }

    // 일정 조회
    // 일정 수정
    // 일정 삭제


}
