package com.tdd.hhplus_tdd_2week_java.common;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.experimental.SuperBuilder;

import java.time.LocalDateTime;

@Getter
@Setter
@SuperBuilder
public class ResponseDto {
    private int code;
    private String message;
    @Builder.Default
    private LocalDateTime time = LocalDateTime.now();
}
