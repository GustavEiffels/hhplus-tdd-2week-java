package com.tdd.hhplus_tdd_2week_java.domain.lecture.service;

import com.tdd.hhplus_tdd_2week_java.common.custom_exceptions.LectureSettingException;
import com.tdd.hhplus_tdd_2week_java.domain.lecture.dto.LectureParam;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;

import java.time.LocalDate;

import static com.tdd.hhplus_tdd_2week_java.domain.lecture.LECTURE_STATUS.LECTURE_CONFLICT;
import static com.tdd.hhplus_tdd_2week_java.domain.lecture.LECTURE_STATUS.NOT_ENOUGH_FIELD;

@Component
public class LectureServiceValidate {

    public void isLectureConflict(int oldStart, int oldEnd, int newStart, int newEnd){
        // 1. 새 강의가 기존 강의의 시작 시간과 겹칠 경우 (단, 종료 시간과 시작 시간이 정확히 일치하는 경우는 제외)
        if (newStart < oldStart && newEnd > oldStart) {
            throw new LectureSettingException(LECTURE_CONFLICT);
        }

        // 2. 새 강의의 종료 시간이 기존 강의의 종료 시간과 겹칠 경우
        if (newStart < oldEnd && newEnd > oldEnd) {
            throw new LectureSettingException(LECTURE_CONFLICT);
        }

        // 3. 새 강의가 기존 강의의 범위 내에 완전히 포함될 경우
        if (newStart >= oldStart && newEnd <= oldEnd) {
            throw new LectureSettingException(LECTURE_CONFLICT);
        }

        // 4. 기존 강의가 새 강의의 범위 내에 완전히 포함될 경우
        if (oldStart >= newStart && oldEnd <= newEnd) {
            throw new LectureSettingException(LECTURE_CONFLICT);
        }

    }

    public void isConditionAssign(LectureParam lectureParam){
        if(lectureParam.getId() == null &&
                lectureParam.getName() == null &&
                lectureParam.getInstructorName() == null &&
                lectureParam.getLocation() == null &&
                lectureParam.getLectureDate() == null &&
                lectureParam.getIsEnrollmentOpen() == null &&
                lectureParam.getStartTime() == null &&
                lectureParam.getEndTime() == null){
            throw new LectureSettingException(NOT_ENOUGH_FIELD);
        }
    }

    public String isConditionFieldNotNull(String stringFieldVal){
        if(!StringUtils.hasText(stringFieldVal)){
            throw new LectureSettingException(NOT_ENOUGH_FIELD);
        }
        return stringFieldVal;
    }

    public Integer isConditionFieldNotNull(Integer intFieldVal){
        if(intFieldVal == null){
            throw new LectureSettingException(NOT_ENOUGH_FIELD);
        }
        return intFieldVal;
    }

    public LocalDate isConditionFieldNotNull(LocalDate localDateFieldVal){
        if(localDateFieldVal == null){
            throw new LectureSettingException(NOT_ENOUGH_FIELD);
        }
        return localDateFieldVal;
    }

    public Boolean isConditionFieldNotNull(Boolean boolFieldVal){
        if(boolFieldVal == null){
            throw new LectureSettingException(NOT_ENOUGH_FIELD);
        }
        return boolFieldVal;
    }
}
