package com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto;

import com.tdd.hhplus_tdd_2week_java.domain.lecture.Lecture;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Builder
public class AppliedLectureResult {
    private Lecture lecture;

    private Student student;

    private LocalDate lectureDate;

    private Integer       startTime;

    private Integer       endTime;
}
