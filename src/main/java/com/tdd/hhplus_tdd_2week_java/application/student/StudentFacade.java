package com.tdd.hhplus_tdd_2week_java.application.student;

import com.tdd.hhplus_tdd_2week_java.common.ResponseDto;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureResult;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentService;
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


    /**
     * @param request
     * @return
     */
    public ResponseDto findAppliedLecture(StudentApiDto.FindAppliedReq request){
        // 존재하는 학생인지 확인
        Student existStudent = studentService.isExistStudent(request.getUserid());

        // 학생 아이디로
        List<LectureResult> lectureResultList = studentService.readLectureResultListById(existStudent.getId());

        return StudentApiDto.FindAppliedRes.builder().lectureInfoList(lectureResultList).build();
    }

}