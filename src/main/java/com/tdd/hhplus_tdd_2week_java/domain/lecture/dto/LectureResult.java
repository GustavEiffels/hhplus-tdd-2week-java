package com.tdd.hhplus_tdd_2week_java.domain.lecture.dto;

import lombok.*;

import java.time.LocalDate;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class LectureResult {
    private Long      id                ;

    private String    name              ;

    private String    instructorName    ;

    private String    location          ;

    private LocalDate lectureDate       ;

    private String    day               ;

    private Boolean   isEnrollmentOpen  ;

    private int       startTime         ;

    private int       endTime           ;
}
