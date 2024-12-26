package com.tdd.hhplus_tdd_2week_java.domain.studuent.service;

import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.StudentSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.STUDENT_STATUS;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentRepository;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentService;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentParam;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class StudentServiceTest {

    @Autowired
    StudentService studentService;


    @Autowired
    StudentRepository repository;

    private Student entitySample;

    private StudentParam paramSample;

    @BeforeEach
    void setUp(){
        paramSample = StudentParam.builder()
                .name("김연습")
                .studentCode("202001001")
                .build();

        entitySample = new Student("김숙달","202401001");
        repository.save(entitySample);
    }

    void exceptionMatcher(StudentParam param, Consumer<StudentParam> method, STUDENT_STATUS status){
        StudentSettingException exception = Assertions.assertThrows(StudentSettingException.class,()->{
            method.accept(param);
        });

        Assertions.assertEquals(status,exception.getStatus());
    }

    /**
     * CREATE
     * - studentCode 가 존재하면 예외
     * - StudentParam 이 비어있으면 예외
     */
    @Test
    void create_exception(){
        exceptionMatcher(
                StudentParam.builder().build(),
                val->studentService.create(val),
                STUDENT_STATUS.NOT_ENOUGH_FIELD
        );

        exceptionMatcher(
                StudentParam.builder().studentCode(entitySample.getStudentCode()).build(),
                val->studentService.create(val),
                STUDENT_STATUS.ALREADY_EXIST
        );


        StudentResult result = studentService.create(StudentParam.builder()
                .studentCode(paramSample.getStudentCode())
                .name(paramSample.getName())
                .build());

        Assertions.assertEquals(paramSample.getStudentCode(),result.getStudentCode());
        Assertions.assertEquals(paramSample.getName(),result.getName());

    }


}