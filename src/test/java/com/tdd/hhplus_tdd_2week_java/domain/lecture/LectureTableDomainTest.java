package com.tdd.hhplus_tdd_2week_java.domain.lecture;

import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.LectureSettingException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import java.time.LocalDate;
import java.util.function.Consumer;


class LectureTableDomainTest {

    private String testStringGenerator(int length){
        StringBuilder sb = new StringBuilder();
        for(int i  = 0; i<length; i++){
            sb.append("t");
        }
        return sb.toString();
    }

    private void exceptionMatcher(
            String fieldName,
            String fieldValue,
            Consumer<String> validationMethod,
            LECTURE_STATUS exception_status){

        LectureSettingException exc = Assertions.assertThrows(LectureSettingException.class, () -> {
            validationMethod.accept(fieldValue);
        });

        if (fieldValue == null) {
            Assertions.assertEquals(LECTURE_STATUS.VALUE_IS_NULL, exc.getStatus(), fieldName + " should throw null value exception.");
        } else {
            Assertions.assertEquals(exception_status, exc.getStatus(), fieldName + " should throw invalid value exception.");
        }
    }



    // name
    /**
     * name : 강의 이름 | 강의 이름은 2글자 이상 50자이하의 영어 한글 숫자만 사용한 문자일 것강의 이름은 2글자 이상 50자이하의 영어 한글 숫자만 사용한 문자일 것
     * -> exception emerege
     * 1. 강의이름 빈값
     * 2. 강의 이름 null
     * 3. 2글자 미만
     * 4. 50 글자 초과
     * 5. 특수문자 사용
     */
    private void name_exceptionMatcher(String lectureName){
        exceptionMatcher(
                "lectureName",
                lectureName,
                value->new Lecture().isValid_LectureNm(value),
                LECTURE_STATUS.INVALID_LECTURE_NAME
        );
    }
    @Test
    void 강의_이름은_2글자_이상_50자이하의_영어_한글_숫자만_사용한문자일_문자일_것(){
        String emptyNm = "";
        String nullNm  = null;
        String underOneChar = "티";
        String over50char   = testStringGenerator(51);
        String specialChar  = "특강!!";
        name_exceptionMatcher(emptyNm);
        name_exceptionMatcher(nullNm);
        name_exceptionMatcher(underOneChar);
        name_exceptionMatcher(over50char);
        name_exceptionMatcher(specialChar);

        Lecture lecture = new Lecture();
        lecture.isValid_LectureNm("김연습의 손톱깎기 특강1");
    }

    // instructorName
    /***
     * instructorName   : 강사 이름 |  한글 또는 영어이고 2자에서 50자 이내여야 할것
     * 1. 빈값 이나 null 안됨
     * 2. 2글자 미만 안됨
     * 3. 50글자 초과 안됨
     * 4. 영어랑 한글 제외 안됨
     */
    private void instructorName_exceptionMatcher(String instructorName){
        exceptionMatcher(
                "instructorName",
                instructorName,
                value->new Lecture().isValid_InstructorName(value),
                LECTURE_STATUS.INVALID_INSTRUCTOR_NAME
        );
    }
    @Test
    void instructorName_강사_이름_한글_또는_영어이고_50자_이내여야_할것(){
        String empty_val  = "";
        String empty_null = null;
        String under_2_character = "가";
        String over_50_character = testStringGenerator(51);
        String only_KoEn = "강사1";
        instructorName_exceptionMatcher(empty_val);
        instructorName_exceptionMatcher(empty_null);
        instructorName_exceptionMatcher(under_2_character);
        instructorName_exceptionMatcher(over_50_character);
        instructorName_exceptionMatcher(only_KoEn);

        // 성공 케이스
        Lecture lecture = new Lecture();
        lecture.isValid_InstructorName("김강사");
    }


    // location
    /**
     * excetpion
     * 2 글지 미만
     * 20 글자 초과
     * 빈값
     * null
     * 영문 한글 숫자 띄어쓰기 가능 이외 사용 시 예외
     * @param location
     */
    private void location_exceptionMatcher(String location){
        exceptionMatcher(
                "location",
                location,
                value->new Lecture().isValid_Location(value),
                LECTURE_STATUS.INVALID_LOCATION_NAME
        );
    }


    @Test
    void location_exceptionCases(){
        String val_empty = "";
        String val_null   = null;
        String val_char_1   = "가";
        String val_char_21  = testStringGenerator(21);
        String val_char_special = "컨퍼런스홀 지하 1층!";

        location_exceptionMatcher(val_empty);
        location_exceptionMatcher(val_null);
        location_exceptionMatcher(val_char_1);
        location_exceptionMatcher(val_char_21);
        location_exceptionMatcher(val_char_special);

        Lecture lecture = new Lecture();
        lecture.isValid_Location("컨퍼런스홀 지하 1층");
    }

    // lectureDate
    /**
     * lectureDate
     * -> exception
     * null x
     * 요일이 일요일 토요일이면 안될 것
     * 현재 날짜 보다 같거나 과거면 안될 것
     */
    private void lectureDate_exceptionMatcher(LocalDate lectureDate){
        Lecture lecture = new Lecture();
        LectureSettingException exc = Assertions.assertThrows(LectureSettingException.class, () -> {
            lecture.isValid_LectureDate(lectureDate);
        });

        if (lectureDate == null) {
            Assertions.assertEquals(LECTURE_STATUS.VALUE_IS_NULL, exc.getStatus(),  "강의 날짜 should throw null value exception.");
        } else {
            Assertions.assertEquals(LECTURE_STATUS.INVALID_LECTURE_DATE, exc.getStatus(), "강의 날짜 should throw invalid value exception.");
        }
    }

    @Test
    void lectureDate_ExceptionTest(){
        LocalDate nullDate = null;
        LocalDate pastDate = LocalDate.now().minusDays(1);
        LocalDate today    = LocalDate.now();
        LocalDate sat      = LocalDate.of(2099,3,7);
        LocalDate sun      = LocalDate.of(2099,3,8);
        lectureDate_exceptionMatcher(nullDate);
        lectureDate_exceptionMatcher(pastDate);
        lectureDate_exceptionMatcher(today);
        lectureDate_exceptionMatcher(sat);
        lectureDate_exceptionMatcher(sun);

        Lecture lecture = new Lecture();
        lecture.isValid_LectureDate(LocalDate.now().plusDays(2));
    }

    // startTime
    /**
     * 09 시 부터 17시 사이어야 함
     */
    private void startTime_exceptionMatcher(int startTime){
        Lecture lecture = new Lecture();
        LectureSettingException exc = Assertions.assertThrows(LectureSettingException.class, () -> {
            lecture.isValid_StartTime(startTime);
        });
        Assertions.assertEquals(LECTURE_STATUS.INVALID_START_TIME, exc.getStatus(), "시작 시간 should throw invalid value exception.");
    }

    @Test
    void startTime_exceptionCase(){
        int under_09 = 8;
        int over_17 = 18;
        startTime_exceptionMatcher(under_09);
        startTime_exceptionMatcher(over_17);

        new Lecture().isValid_StartTime(10);
    }

    // endTime
    /**
     * exception
     * 10시 미만 , 18 시 초과 시 에러
     */
    private void endTime_exceptionMatcher(int endTime){
        Lecture lecture = new Lecture();
        LectureSettingException exc = Assertions.assertThrows(LectureSettingException.class, () -> {
            lecture.isValid_EndTime(endTime);
        });
        Assertions.assertEquals(LECTURE_STATUS.INVALID_END_TIME, exc.getStatus(), "종료 시간 should throw invalid value exception.");
    }
    @Test
    void endTime_exceptionCase(){
        int under_10 = 9;
        int over_18 = 19;
        endTime_exceptionMatcher(under_10);
        endTime_exceptionMatcher(over_18);

        new Lecture().isValid_StartTime(11);
    }


    /**
     * end - start 는 1 이상 3 미만 이어야한다 .
     */
    private void runningTime_exceptionMatcher(int startTime, int endTime){
        Lecture lecture = new Lecture();
        LectureSettingException exc = Assertions.assertThrows(LectureSettingException.class, () -> {
            lecture.isValid_RunningTime(startTime,endTime);
        });
        Assertions.assertEquals(LECTURE_STATUS.INVALID_RUNNING_TIME, exc.getStatus(), "특강 운영 should throw invalid value exception.");
    }

    @Test
    void runningTime_exceptionCase(){
        runningTime_exceptionMatcher(9,13);
        runningTime_exceptionMatcher(13,13);
        runningTime_exceptionMatcher(14,13);
        runningTime_exceptionMatcher(10,14);
    }


}