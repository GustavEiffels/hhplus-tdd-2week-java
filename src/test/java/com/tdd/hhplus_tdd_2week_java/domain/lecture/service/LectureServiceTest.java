package com.tdd.hhplus_tdd_2week_java.domain.lecture.service;


import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.LectureSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LECTURE_STATUS;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.Lecture;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LectureRepository;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.LectureService;
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
import java.util.List;
import java.util.Optional;
import java.util.function.BiConsumer;
import java.util.function.Consumer;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

@SpringBootTest
@Transactional
@ActiveProfiles("test")
class LectureServiceTest {


    @Autowired
    LectureService lectureService;

    @Autowired
    LectureRepository lectureRepository;

    private Lecture entitySample;

    private LectureParam paramSample;

    private void exceptionMatcher(LectureParam findLectureParam, Consumer<LectureParam> method, LECTURE_STATUS status){
        LectureSettingException exception = Assertions.assertThrows(LectureSettingException.class,()->{
            method.accept(findLectureParam);
        });
        Assertions.assertEquals(status,exception.getStatus());
    }

    private void exceptionMatcher(LectureParam findLectureParam, LectureParam updateLectureParam, BiConsumer<LectureParam,LectureParam> method, LECTURE_STATUS status){
        LectureSettingException exception = Assertions.assertThrows(LectureSettingException.class,()->{
            method.accept(findLectureParam,updateLectureParam);
        });
        Assertions.assertEquals(status,exception.getStatus());
    }

    @BeforeEach
    void setUp(){
        // Create lecture before
        paramSample = LectureParam.builder()
                .name("밥먹는 방법 01")
                .instructorName("김밥")
                .location("본관 303호")
                .lectureDate(LocalDate.of(2025,12,30))
                .startTime(10)
                .endTime(12)
                .build();

        entitySample = new Lecture(
                "숨쉬기 방법 01",
                "김한숨",
        "아고라존 1001호",
                LocalDate.of(2025,1,2),
                10,
                12
        );
        lectureRepository.save(entitySample);
    }

    /**
     * readByCondition
     * : 조건에 따라 데이터를 읽음
     * exception
     * - 조회 조건이 null 이면 예외
     */
    @Test
    void readyByCondition_Exception(){
        exceptionMatcher(
                LectureParam.builder().build(),
                val->lectureService.readByCondition(val),
                LECTURE_STATUS.NOT_ENOUGH_FIELD
                );
    }

    // ** 성공 테스트
    @Test
    void readyByCondition_Valid(){
        LectureParam lectureParam = LectureParam.builder()
                .name("숨쉬기 방법 01")
                .build();
        Optional<LectureResult> findCondition = lectureService.readByCondition(lectureParam);
        assertTrue(findCondition.isPresent());
        assertEquals(findCondition.get().getLectureDate(),LocalDate.of(2025,1,2));
        assertEquals(findCondition.get().getInstructorName(),"김한숨");
        assertEquals(findCondition.get().getLocation(),entitySample.getLocation());
    }

    /**
     * Create
     * - lectureParam 이 비어있으면
     * - 강의실 이름, 날짜 데이터, 시작시간, 종료 시간 이 존재하지 않을때 예외
     * - 같은 날, 같은 강의실에 시간이 겹치면 -> LECTURE_CONFLICT
     */
    @Test
    void create_Exception(){
        paramSample.setLocation(entitySample.getLocation());
        paramSample.setLectureDate(entitySample.getLectureDate());
        paramSample.setStartTime(entitySample.getStartTime()-1);
        paramSample.setEndTime(entitySample.getEndTime());

        exceptionMatcher(paramSample,val->lectureService.create(val),LECTURE_STATUS.LECTURE_CONFLICT);
        exceptionMatcher(LectureParam.builder().build(),val->lectureService.create(val),LECTURE_STATUS.NOT_ENOUGH_FIELD);

        paramSample.setStartTime(null);
        exceptionMatcher(paramSample,val->lectureService.create(val),LECTURE_STATUS.NOT_ENOUGH_FIELD);
    }


    @Test
    void create_valid(){
        LectureResult result = lectureService.create(paramSample);

        assertEquals(paramSample.getLocation(),result.getLocation());
        assertEquals(paramSample.getName(),result.getName());
        assertEquals(paramSample.getInstructorName(),result.getInstructorName());
    }

    /**
     * Update
     * - exception
     * - lectureParam 이 null 인지 확인
     * - lecture 못찾으면 예외
     */
    @Test
    void update_exception(){

        // param 필드 null => 검색 조건 없음
        exceptionMatcher(
                LectureParam.builder().build(),
                paramSample,
                (val1,val2)->lectureService.update(val1,val2),
                LECTURE_STATUS.NOT_ENOUGH_FIELD);

        // not found
        exceptionMatcher(
                LectureParam.builder().name(paramSample.getName()).build(),
                paramSample,
                (val1,val2)->lectureService.update(val1,val2),
                LECTURE_STATUS.NOT_FOUND_LECTURE);
    }

    @Test
    void update_valid(){
        LectureParam condition = LectureParam.builder()
                .name(entitySample.getName())
                .build();

        LectureResult result = lectureService.update(condition,paramSample);
        // 인간 오류를 많이 고침
        Assertions.assertEquals(paramSample.getInstructorName(),result.getInstructorName());
        Assertions.assertEquals(paramSample.getLocation(),result.getLocation());

    }

    /**
     * readAllByCondition
     * - exception
     * 조건이 없으면 에러
     *
     * - 검색 조건에 맞는게 없으면 null 을 리턴
     */
    @Test
    void readAllByCondition_exception(){
        exceptionMatcher(
                LectureParam.builder().build(),
                val->lectureService.readAllByCondition(val),
                LECTURE_STATUS.NOT_ENOUGH_FIELD
        );
    }

    @Test
    void readAllByCondition_valid(){

        // 존재하지 않으면 0
        List<LectureResult> resultList = lectureService.readAllByCondition(paramSample);
        assertEquals(0,resultList.size());

        // 존재함 1
        List<LectureResult> resultsExist = lectureService.readAllByCondition(LectureParam.builder().name(entitySample.getName()).build());
        assertEquals(1,resultsExist.size());
    }




}