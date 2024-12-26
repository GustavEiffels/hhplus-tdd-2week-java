package com.tdd.hhplus_tdd_2week_java.application.student;

import com.tdd.hhplus_tdd_2week_java.common.ResponseDto;
import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.StudentSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureResult;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.STUDENT_STATUS;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentService;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentServiceValidate;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.dto.StudentParam;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class StudentFacade {

    private final StudentService studentService;

    private final StudentServiceValidate studentServiceValidate;

    public ResponseDto findAppliedLecture(StudentApiDto.FindAppliedReq request){
        // user id 가 Null 인지 확인
        studentServiceValidate.isConditionFieldNotNull(request.getUserid());

        // user id 로 학생이 존재하는 지 확인
        // 1. 조건 생성
        StudentParam        condition = StudentParam.builder().id(request.getUserid()).build();

        // 2. 조회
        Optional<Student> existStudent = studentService.readByCondition(condition);


        if(existStudent.isEmpty()){
            throw new StudentSettingException(STUDENT_STATUS.NOT_EXIST_STUDENT);
        }

        List<LectureResult> lectureResultList = studentService.readLectureResultListById(existStudent.get().getId());

        return StudentApiDto.FindAppliedRes.builder().lectureInfoList(lectureResultList).build();
    }

}
