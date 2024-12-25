package com.tdd.hhplus_tdd_2week_java.domain.studuent.service;

import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentRepository;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentService;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentParam;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentResult;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository repository;

    @Override
    public StudentResult create(StudentParam studentParam) {
        return null;
    }

    @Override
    public StudentResult update(StudentParam findParam, StudentParam updateParam) {
        return null;
    }

    @Override
    public StudentResult convertToDto(Student student) {
        return null;
    }

    @Override
    public Student convertToEntity(StudentParam studentParam) {
        return null;
    }


}
