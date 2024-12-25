package com.tdd.hhplus_tdd_2week_java.domain.studuent.dto;

import lombok.*;

@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class StudentResult {
    private Long id;

    private String name;

    private String studentCode;
}
