package com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto;

import com.tdd.hhplus_tdd_2week_java.domain.lecture.Lecture;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class AppliedLectureResult {
    private Long id;
    private Lecture lecture;

    private Student student;

    private LocalDate lectureDate;

    private Integer       startTime;

    private Integer       endTime;
}
