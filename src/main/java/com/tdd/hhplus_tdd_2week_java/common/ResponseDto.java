package com.tdd.hhplus_tdd_2week_java.common;

import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;
import java.time.LocalDateTime;

@Getter
@Setter
public class ResponseDto {
    private int code;
    private String message;
    private LocalDateTime time = LocalDateTime.now();
}
