package com.example.calendar.entity;

import jakarta.persistence.Column;
import jakarta.persistence.EntityListeners;
import jakarta.persistence.MappedSuperclass;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@Getter
@MappedSuperclass
@EntityListeners(AuditingEntityListener.class)
public abstract class BaseEntity {

    // 생성 시간
    @CreatedDate
    // 생성 시간은 수정되면 안됨.
    @Column(updatable = false)
    private LocalDateTime createdAt;

    // 수정 시간
    @LastModifiedDate
    private LocalDateTime modifiedAT;
}
