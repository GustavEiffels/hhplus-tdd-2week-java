package com.tdd.hhplus_tdd_2week_java.application.lecture;

import com.tdd.hhplus_tdd_2week_java.common.ResponseDto;
import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.AppliedLectureSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.APPLIED_LECTURE_STATUS;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLecture;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLectureRepository;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.AppliedLectureService;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.Lecture;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LectureRepository;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LectureService;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureResult;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentRepository;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentService;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class LectureFacadeTest {

    @Autowired
    LectureFacade lectureFacade;

    @Autowired
    LectureService lectureService;

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    AppliedLectureRepository appliedLectureRepository;

    @Autowired
    StudentRepository studentRepository;

    public Lecture createLecture(int year, int mon, int day, int start, int end){
        return lectureRepository.save(lectureService.convertToEntity(LectureParam.builder()
                .name("바보들")
                .instructorName("김연습")
                .location("집202")
                .lectureDate(LocalDate.of(year,mon,day))
                .startTime(start)
                .endTime(end)
                .build()));
    }

    public Student createStudent(String name, String code){
        return studentRepository.save(new Student(name,code));
    }

    public AppliedLecture createAppliedLecture(Lecture lecture,Student student){
        return appliedLectureRepository.save(new AppliedLecture(lecture,student));
    }


    /**
     * 날짜 기준으로 신청 가능 목록 API
     * findListByLecture
     */
    @Test
    void findListByLecture(){
        Lecture lecture_0 = createLecture(2024,12,31,10,11);
        Lecture lecture_1 = createLecture(2024,12,31,12,13);
        Lecture lecture_2 = createLecture(2024,12,31,14,17);
        Lecture lecture_3 = createLecture(2024,12,30,14,17); // 날짜 안맞음
        Lecture lecture_4 = createLecture(2024,12,31,11,12); // isEnrollmentOpen 이 false 임
        lecture_4.updateEnrollmentOpen(false);
        lectureRepository.save(lecture_4);

        LectureApiDto.FindByLocalReq req =  LectureApiDto.FindByLocalReq.builder()
                .localDate(LocalDate.of(2024,12,31))
                .build();

        LectureApiDto.FindByLocalRes responseDto = lectureFacade.findListByLecture(req);
        Assertions.assertEquals(3,responseDto.getLectureList().size());
    }

    @Test
    void 같은_사용자가_동일한_특강에_대해_신청_성공못하게_하기(){

        // 0. lecture 와  student 생성
        Lecture createLecture = createLecture(2024,12,31,10,11);
        Student createStudent = createStudent("김하수","202202022");


        // 1. RequestDto 생성
        LectureApiDto.ApplyLectureReq req = LectureApiDto.ApplyLectureReq.builder()
                .lectureId(createLecture.getId())
                .userId(createStudent.getId())
                .build();

        lectureFacade.applyLecture(req);


            for (int i = 0; i < 4; i++) {
                AppliedLectureSettingException exception =
                        Assertions.assertThrows(AppliedLectureSettingException.class, () -> {
                    lectureFacade.applyLecture(req);
                });
                System.out.println(exception.getStatus());
//                Assertions.assertEquals(APPLIED_LECTURE_STATUS.ALREADY_EXIST,exception.getStatus(),"이미 신청한 강의");
            }

    }

}