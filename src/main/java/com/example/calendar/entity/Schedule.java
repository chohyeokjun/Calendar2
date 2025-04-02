package com.example.calendar.entity;

import jakarta.persistence.*;
import lombok.Getter;


@Entity
@Getter
@Table(name = "schedule")
// BaseEntity 로부터 생성 시간과 수정 시간을 자동 Mapping 받음
public class Schedule extends BaseEntity{
    // id
    @Id
    // pk 생성 (전략으로 자동 증가 = AUTO_INCREMENT 와 같은 기능)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 작성명
    @ManyToOne
    @JoinColumn(name = "user_id")
    private User user;

    // 할일 제목
    @Column(nullable = false) // null 값 불허
    private String title;

    // 할일 내용
    @Column(columnDefinition = "longtext") // 긴 text 가능
    private String task;

    // entity 기본 생성자 필수!
    public Schedule () {}

    public Schedule (String username, String title, String task) {
        this.title = title;
        this.task = task;
    }

    // 일정 수정 메서드
    public void updateSchedule(String title, String task) {
        this.title = title;
        this.task = task;
    }

    public void setUser (User user) {
        this.user = user;
    }
}
