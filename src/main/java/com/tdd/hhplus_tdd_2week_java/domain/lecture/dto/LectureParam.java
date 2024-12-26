package com.tdd.hhplus_tdd_2week_java.domain.lecture.dto;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;


/**
 * Service 를 호출할 때 필요한 DTO
 */
@Getter
@Setter
@Builder
public class LectureParam {
    private Long      id;

    private String    name              ;

    private String    instructorName    ;

    private String    location          ;

    private LocalDate lectureDate       ;

    private Boolean   isEnrollmentOpen  ;

    private Integer       startTime         ;

    private Integer       endTime           ;
}
