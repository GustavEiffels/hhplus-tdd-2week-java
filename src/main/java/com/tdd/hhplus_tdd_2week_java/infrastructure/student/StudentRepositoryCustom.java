package com.tdd.hhplus_tdd_2week_java.infrastructure.student;

import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentParam;

import java.util.Optional;

public interface StudentRepositoryCustom {
    Optional<Student> findByCondition(StudentParam condition);


}
