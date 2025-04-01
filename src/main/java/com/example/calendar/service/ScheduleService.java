package com.example.calendar.service;

import com.example.calendar.dto.ScheduleResponseDto;
import com.example.calendar.entity.Schedule;
import com.example.calendar.repository.ScheduleRepository;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

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

    public List<ScheduleResponseDto> findAllSchedule() {
//        List<Schedule> findList = scheduleRepository.findAll();
//        // schedule 타입의 리스트를 어떻게 dto형태로 넘겨줄까....
//        List<ScheduleResponseDto> scheduleResponseDtoList = new ArrayList<>();
//
//        for (Schedule schedule : findList) {
//            scheduleResponseDtoList.add(new ScheduleResponseDto(schedule.getId(), schedule.getTitle(), schedule.getTask()));
//        }

        // stream 으로
        return scheduleRepository.findAll()
                .stream()
                .map(schedule -> new ScheduleResponseDto(
                        schedule.getId(),
                        schedule.getTitle(),
                        schedule.getTask()
                ))
                .toList();
    }


    @Transactional
    public void updateSchedule(Long id, String title, String task) {

        // 일정 조회
        Schedule findSchedule = scheduleRepository.findByIdOrElseThrow(id);

        // 할일 제목, 할일 내용 수정
        findSchedule.updateSchedule(title, task);

        // save 로 수정
//        scheduleRepository.save(findSchedule(title, task));
    }

    public void delete(Long id) {
        // delete() - 괄호 안에 객체가 들어가야 하므로 삭제할 객체 조회
        Schedule findScehdule = scheduleRepository.findByIdOrElseThrow(id);
        scheduleRepository.delete(findScehdule);
    }
}
