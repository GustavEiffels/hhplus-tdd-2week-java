package com.tdd.hhplus_tdd_2week_java.domain.lecture;


import com.tdd.hhplus_tdd_2week_java.domain.BaseEntity;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLecture;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

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
public class Lecture extends BaseEntity {

    // 모든 Entity 의 id 값을 long 으로 사용한다는 보장이 없음
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

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

    @OneToMany(mappedBy = "lecture", cascade = CascadeType.REMOVE)
    @Builder.Default
    public List<AppliedLecture> appliedLectures = new ArrayList<>();

}
