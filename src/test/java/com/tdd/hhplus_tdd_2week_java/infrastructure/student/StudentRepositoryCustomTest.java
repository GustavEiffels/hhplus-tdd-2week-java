package com.tdd.hhplus_tdd_2week_java.infrastructure.student;


import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentRepository;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentParam;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class StudentRepositoryCustomTest {

    @Autowired
    private StudentRepository repository;

    private Student studentEntity;

    @BeforeEach
    void setUp(){
        studentEntity = new Student("김연습","202202022");
        repository.save(studentEntity);
    }


    /**
     * findByCondition
     * StudentParam 을 가지고 조회
     * StudentParam 을 가지고 조회
     */
    @Test
    void findByCondition(){
        Optional<Student> student = repository.findByCondition(StudentParam.builder().studentCode("202202022").build());
        assertTrue(student.isPresent());

        Student existStudent = student.get();
        assertEquals(studentEntity.getName(),existStudent.getName());
    }



}