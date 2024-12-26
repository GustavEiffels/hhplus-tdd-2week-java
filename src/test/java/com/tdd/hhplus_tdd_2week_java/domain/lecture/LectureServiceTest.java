package com.tdd.hhplus_tdd_2week_java.domain.lecture;

import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureParam;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureResult;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;


@SpringBootTest
@Transactional
@ActiveProfiles("test")
class LectureServiceTest {

    @Autowired
    LectureService lectureService;

    @Autowired
    LectureRepository lectureRepository;

    Lecture newLecture;

    @BeforeEach
    void setUp(){
        newLecture = new Lecture(
                "숨쉬기 기능 1",
                "김연습",
                "303호",
                LocalDate.of(2024,12,31),
                9,
                12);
        lectureRepository.save(newLecture);
    }

    // CREATE
    @Test
    void create_lecture(){
        LectureParam lectureParam = LectureParam.builder()
                .name("걷기 운동 1")
                .instructorName("이강사")
                .location("303호")
                .lectureDate(LocalDate.of(2024,12,31))
                .startTime(12)
                .endTime(15)
                .build();

        LectureResult result =  lectureService.create(lectureParam);
        Assertions.assertEquals(lectureParam.getLocation(),result.getLocation());
    }

    // READ
    @Test
    void read_lecture(){
        LectureParam condition = LectureParam.builder()
                .location(newLecture.getLocation())
                .build();

        Optional<Lecture> lectureResult = lectureRepository.findByCondition(condition);

        Assertions.assertTrue(lectureResult.isPresent());
    }

}