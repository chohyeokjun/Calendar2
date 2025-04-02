package com.example.calendar.entity;

import jakarta.persistence.*;
import lombok.Getter;

@Entity
@Table
@Getter
public class User extends BaseEntity{

    // 기본키 생성
    @Id
    // pk 생성 (전략으로 자동 증가 = AUTO_INCREMENT 와 같은 기능)
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    // 유저명
    private String username;

    // 이메일
    @Column(nullable = false) // Not null
    private String email;

    // 기본 생성자 필수!!
    public User(){}

    public User (String username, String email) {
        this.username = username;
        this.email = email;
    }

    // 수정 메서드
    public void updateUser (String email){
        this.email = email;
    }
}
