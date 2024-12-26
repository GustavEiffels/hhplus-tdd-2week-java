package com.tdd.hhplus_tdd_2week_java.domain.studuent;

import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentParam;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentResult;

public interface StudentService {

    StudentResult create(StudentParam studentParam);

    StudentResult convertToDto(Student student);

    Student convertToEntity(StudentParam studentParam);
}
