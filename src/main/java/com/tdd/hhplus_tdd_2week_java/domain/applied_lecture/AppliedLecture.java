package com.tdd.hhplus_tdd_2week_java.domain.applied_lecture;


import com.tdd.hhplus_tdd_2week_java.domain.BaseEntity;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.Lecture;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import jakarta.persistence.*;
import lombok.*;

import java.time.LocalDate;

@Entity
@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor(access = AccessLevel.PACKAGE)
@Table(name = "applied_lecture")
public class AppliedLecture extends BaseEntity {

    // 모든 Entity 의 id 값을 long 으로 사용한다는 보장이 없음
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "lecture")
    private Lecture lecture;


    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "student")
    private Student   student;

    private LocalDate lectureDate;

    private int       startTime;

    private int       endTime;


}
