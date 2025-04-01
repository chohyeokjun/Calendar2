package com.example.calendar.service;

import com.example.calendar.dto.ScheduleResponseDto;
import com.example.calendar.entity.Schedule;
import com.example.calendar.repository.ScheduleRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class ScheduleService {

    // repository 접근
    private final ScheduleRepository scheduleRepository;

    public ScheduleResponseDto saveSchedule(String username, String title, String task) {
        // Schedule 객체 생성
        Schedule schedule = new Schedule(username, title, task);
        // 객체 정보 저장
        Schedule saveSchedule = scheduleRepository.save(schedule);

        return new ScheduleResponseDto(saveSchedule.getId(), saveSchedule.getTitle(), saveSchedule.getTask());
    }
}
