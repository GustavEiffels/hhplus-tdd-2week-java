package com.tdd.hhplus_tdd_2week_java.application.lecture;

import com.tdd.hhplus_tdd_2week_java.common.ResponseDto;
import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.AppliedLectureSettingException;
import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.LectureSettingException;
import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.StudentSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.APPLIED_LECTURE_STATUS;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLecture;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLectureService;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLectureServiceValidation;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LECTURE_STATUS;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.Lecture;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LectureService;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureResult;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.service.LectureServiceValidate;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentService;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentServiceValidate;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

import static com.tdd.hhplus_tdd_2week_java.domain.lecture.LECTURE_STATUS.PARTICIPANT_FULL;

@Service
@RequiredArgsConstructor
@Transactional(readOnly = true)
public class LectureFacade {

    private final LectureService lectureService;
    private final LectureServiceValidate lectureServiceValidate;
    private final StudentService studentService;
    private final AppliedLectureService appliedLectureService;


    /**
     * 날짜 기준으로 신청 가능 목록 API
     * @param request
     * @return
     */
    public LectureApiDto.FindByLocalRes findListByLecture(LectureApiDto.FindByLocalReq request){
        // request 객체 null 인지 확인
        lectureServiceValidate.isConditionFieldNotNull(request.getLocalDate());

        // 검색 조건 생성
        LectureParam condition = LectureParam.builder()
                .lectureDate(request.getLocalDate())
                .isEnrollmentOpen(true)
                .build();

        // 검색 결과 가져오기
        List<LectureResult> lectures = lectureService.readAllWithResult(condition);

        return LectureApiDto.FindByLocalRes.builder()
                .lectureList(lectures)
                .build();
    }

    /**
     * 특강 신청하는 API
     */
    @Transactional
    public ResponseDto applyLecture(LectureApiDto.ApplyLectureReq request){


        // 학생이 존재하는지 확인
        Student student = studentService.isExistStudent(request.getUserId());

        // lecture 존재하는지 확인
        Lecture lecture = lectureService.isLectureExist(request.getLectureId());

        // 신청 가능한지
        if(!lecture.getIsEnrollmentOpen()){
            throw new LectureSettingException(PARTICIPANT_FULL);
        }

        // 현재 강의 몇명이 신청 했는지
        List<AppliedLecture>   currentAppliedLecture = appliedLectureService.readAllWithEntity(
                AppliedLectureParam.builder().lecture(lecture).build()
        );


        // appliedLecture 생성
        Optional<AppliedLecture> existAppliedLecture = appliedLectureService.isExistAppliedLecture(
                AppliedLectureParam.builder().lecture(lecture).student(student).build()
        );

        // 이미 해당 강의 신청한지 확인
        if(existAppliedLecture.isPresent()){
            throw new AppliedLectureSettingException(APPLIED_LECTURE_STATUS.ALREADY_EXIST);
        }

        // 오늘 강의랑 겹치는게 있는지 확인
        for(LectureResult lectureResult : studentService.getTodaySchedule(student.getId(),lecture.getLectureDate())){
            lectureServiceValidate.isLectureConflict(
                    lectureResult.getStartTime(),
                    lectureResult.getEndTime(),
                    lecture.getStartTime(),
                    lecture.getEndTime());
        }

        // CREATE
        appliedLectureService.create(AppliedLectureParam.builder().lecture(lecture).student(student).build());

        // UPDATE
        if(currentAppliedLecture.size()==29){
            lectureService.updateWithEntity(lecture,LectureParam.builder().isEnrollmentOpen(false).build());
        }

        return LectureApiDto.ApplyLectureRes.builder().code(201).message("APPLY COMPLETE").build();
    }


}
