package com.tdd.hhplus_tdd_2week_java.entity;

import jakarta.persistence.*;
import lombok.Getter;
import org.springframework.data.annotation.CreatedDate;
import org.springframework.data.annotation.LastModifiedDate;
import org.springframework.data.jpa.domain.support.AuditingEntityListener;

import java.time.LocalDateTime;

@MappedSuperclass
@Getter
@EntityListeners(AuditingEntityListener.class)
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long pid;

    @CreatedDate
    @Column(updatable = false)
    private LocalDateTime createDateTime;

    @LastModifiedDate
    private LocalDateTime updateDateTime;


}
