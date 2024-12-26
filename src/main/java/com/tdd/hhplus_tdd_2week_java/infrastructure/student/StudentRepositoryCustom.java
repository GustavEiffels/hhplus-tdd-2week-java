package com.tdd.hhplus_tdd_2week_java.infrastructure.student;

import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentRepository;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentParam;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentResult;

import java.util.List;
import java.util.Optional;

public interface StudentRepositoryCustom {
    Optional<Student> findByCondition(StudentParam condition);

    Optional<StudentResult> findByConditionWithResult(StudentParam condition);


    List<Student> findAllByCondition(StudentParam condition);

    List<StudentResult> findAllByConditionWithResult(StudentParam condition);


}
