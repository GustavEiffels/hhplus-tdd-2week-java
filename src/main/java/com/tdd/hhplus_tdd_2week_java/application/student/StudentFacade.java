package com.tdd.hhplus_tdd_2week_java.application.student;

import com.tdd.hhplus_tdd_2week_java.common.ResponseDto;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentService;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentServiceValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentFacade {

    private final StudentService studentService;

    private final StudentServiceValidate studentServiceValidate;

    ResponseDto findAppliedLecture(StudentApiDto.FindAppliedReq request){
        // user id 가 Null 인지 확인
        studentServiceValidate.isConditionFieldNotNull(request.getUserid());

        // user id 로 학생이 존재하는 지 확인
//        Optional<Student> existStudent =

        return null;
    }

}
