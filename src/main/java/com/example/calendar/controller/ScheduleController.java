package com.example.calendar.controller;

import com.example.calendar.dto.ScheduleRequestDto;
import com.example.calendar.dto.ScheduleResponseDto;
import com.example.calendar.dto.ScheduleUpdateRequestDto;
import com.example.calendar.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
    @GetMapping
    public ResponseEntity<List<ScheduleResponseDto>> findAllSchedule () {
        List<ScheduleResponseDto> allSchedule = scheduleService.findAllSchedule();
        return new ResponseEntity<>(allSchedule, HttpStatus.OK);
    }

    // 일정 수정
    @PutMapping("/{id}")
    public ResponseEntity<Void> updateSchedule (@PathVariable Long id, @RequestBody ScheduleUpdateRequestDto updateRequestDto) {
        scheduleService.updateSchedule(id, updateRequestDto.getTitle(), updateRequestDto.getTask());
        return new ResponseEntity<>(HttpStatus.OK);
    }

    // 일정 삭제
    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete (@PathVariable Long id) {
        scheduleService.delete(id);
        return new ResponseEntity<>(HttpStatus.OK);
    }


}

