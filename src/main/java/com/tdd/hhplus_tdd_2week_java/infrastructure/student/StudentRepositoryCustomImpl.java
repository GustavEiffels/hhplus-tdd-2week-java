package com.tdd.hhplus_tdd_2week_java.infrastructure.student;


import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentParam;

import java.util.Optional;

public class StudentRepositoryCustomImpl implements StudentRepositoryCustom{

    @Override
    public Optional<Student> findByCondition(StudentParam condition) {
        return Optional.empty();
    }
}
