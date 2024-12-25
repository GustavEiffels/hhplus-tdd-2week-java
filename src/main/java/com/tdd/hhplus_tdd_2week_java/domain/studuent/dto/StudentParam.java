package com.tdd.hhplus_tdd_2week_java.domain.studuent.dto;


import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentParam {
    private Long id;

    private String name;

    private String studentCode;
}
