package com.example.calendar.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Getter
@Table
// BaseEntity 로부터 생성 시간과 수정 시간을 자동 Mapping 받음
public class Schedule extends BaseEntity{
    // id
    @Id
    // pk 생성 (전략으로 자동 증가 = AUTO_INCREMENT 와 같은 기능)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 작성명
    private String username;

    // 할일 제목
    @Column(nullable = false) // null 값 불허
    private String title;

    // 할일 내용
    @Column(columnDefinition = "longtext") // 긴 text 가능
    private String task;

    // entity 기본 생성자 필수!
    public Schedule () {}

    public Schedule (String username, String title, String task) {
        this.username = username;
        this.title = title;
        this.task = task;
    }
}
