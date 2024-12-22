package com.tdd.hhplus_tdd_2week_java.entity;


import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
/**
 * name : 강의 이름은 100 글자를 넘기지 않을 것
 * instructorName   : 강사 이름 한글 또는 영어이고 50자 이내여야 할것
 * location         : 강의실 위치 이름은 20 자리 이내 이어야 할 것
 * lectureDate      : 생성시  현재 날짜보다 뒤에 있어야함
 * day              : mon, tue, wed,thr, fri 만 존재
 * isEnrollmentOpen : 강의 수강 가능한지-> default false
 * startTime        : 09<= 시작 시간 <= 17
 * endTime          : 10<= 종료 시간 <= 18
 */
public class Lecture extends BaseEntity{

    @Column(nullable = false)
    private String    name              ; // 강의 이름
    @Column(nullable = false)
    private String    instructorName    ;
    @Column(nullable = false)
    private String    location          ;
    @Column(nullable = false)
    private LocalDate lectureDate       ;
    @Column(nullable = false)
    private Character day               ;
    @Column(nullable = false)
    private Boolean   isEnrollmentOpen  ;
    @Column(nullable = false)
    private int       startTime         ;
    @Column(nullable = false)
    private int       endTime           ;

}
