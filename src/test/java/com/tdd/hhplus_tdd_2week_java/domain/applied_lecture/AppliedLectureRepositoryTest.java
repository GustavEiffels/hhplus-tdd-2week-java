package com.tdd.hhplus_tdd_2week_java.domain.applied_lecture;

import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.applied_lecture.dto.AppliedLectureResult;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.Lecture;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LectureRepository;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.Student;
import com.tdd.hhplus_tdd_2week_java.domain.studuent.StudentRepository;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class AppliedLectureRepositoryTest {

    private Student studentEntity;

    private Lecture lectureEntity;

    private AppliedLecture appliedLectureEntity;

    @Autowired
    LectureRepository lectureRepository;

    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AppliedLectureRepository appliedLectureRepository;

    @BeforeEach
    void setUp(){
        studentEntity = new Student("김연습","202202002");
        lectureEntity = new Lecture(
                "숨쉬기 연습 1",
                "김강사",
                "본관 1층",
                LocalDate.of(2024,12,30),
                9,
                12);

        studentRepository.save(studentEntity);
        lectureRepository.save(lectureEntity);

        Lecture lecture2 = lectureRepository.save(
                new Lecture(
                        "숨쉬기 연습 1",
                        "김강사",
                        "본관 2층",
                        LocalDate.of(2024,12,30),
                        13,
                        15)
        );
        appliedLectureEntity = new AppliedLecture(lectureEntity,studentEntity);
        appliedLectureRepository.save(appliedLectureEntity);
        appliedLectureRepository.save(new AppliedLecture(lecture2,studentEntity));
    }

    @Test
    void findByCondition(){
        AppliedLectureParam param =
                AppliedLectureParam.builder()
                        .student(studentEntity)
                        .build();

        Optional<AppliedLecture> appliedLecture = appliedLectureRepository.findByCondition(param);

        Assertions.assertTrue(appliedLecture.isPresent());
        AppliedLecture findCondition = appliedLecture.get();

        Assertions.assertEquals(lectureEntity.getLectureDate(),findCondition.getLectureDate());
    }

    @Test
    void findAllByCondition(){
        AppliedLectureParam param_lectureDate =
                AppliedLectureParam.builder()
                        .lectureDate(lectureEntity.getLectureDate())
                        .build();

        List<AppliedLectureResult> resultListByLectureDate =
                appliedLectureRepository.findAllByCondition(param_lectureDate);

        Assertions.assertEquals(2,resultListByLectureDate.size());

    }

}