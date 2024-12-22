package com.tdd.hhplus_tdd_2week_java.service.student;

import com.tdd.hhplus_tdd_2week_java.entity.Student;
import com.tdd.hhplus_tdd_2week_java.repository.student.StudentRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
@RequiredArgsConstructor
public class StudentServiceImpl implements StudentService {

    private final StudentRepository studentRepository;

}
